/**
 * Free & Fair Election Security 101 System
 * 
 * @title ElectionSecurity101
 * @created Mar 1, 2017
 * @copyright 2017 Free & Fair
 * @license BSD 3-Clause License
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 * @description A web-based demonstration that illustrates the relative security
 *              of various voting methods in common use in the United States.
 */

package us.freeandfair.es101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jmlspecs.annotation.Pure;
import org.stringtemplate.v4.ST;

import spark.Request;
import spark.Response;
import spark.Spark;
import us.freeandfair.es101.util.StringTemplateUtil;

/**
 * The main executable of the system. Starts the web server and an election
 * using the properties file specified on the command line.
 * 
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 * @version 1.0
 */
public class Main {
  /**
   * The name of the default properties resource.
   */
  public static final String DEFAULT_PROPERTIES = "us/freeandfair/es101/default.properties";

  /**
   * The name of the logger.
   */
  public static final String LOGGER_NAME = "election";

  /**
   * The default port number.
   */
  public static final int DEFAULT_PORT = 8888;

  /**
   * The minimum allowed port number.
   */
  public static final int MIN_PORT = 1000;

  /**
   * The maximum allowed port number.
   */
  public static final int MAX_PORT = 65535;

  /**
   * The logger.
   */
  public static final Logger LOGGER = LogManager.getLogger(LOGGER_NAME);

  /**
   * The properties loaded from the properties file.
   */
  private final Properties my_properties;

  /**
   * The queue of incoming voter actions.
   */
  private final Queue<VoterAction> my_voter_action_queue;

  /**
   * The election we're running.
   */
  private final Election my_election;
  
  /**
   * The timeout after which votes in the queue are cast.
   */
  private long my_vote_timeout;
  
  /**
   * The timeout after which the system is checkpointed.
   */
  private long my_checkpoint_timeout = 180000;
  
  /**
   * The last checkpoint time.
   */
  private long my_last_checkpoint_time;
  
  /**
   * The filesystem path where checkpoints should be written and read.
   */
  private String my_checkpoint_file;
  
  /**
   * Constructs a new Main with the specified properties.
   * 
   * @param the_properties The properties.
   */
  public Main(final Properties the_properties) {
    my_properties = the_properties;
    my_election = parseProperties(the_properties);
    my_voter_action_queue = my_election.my_queue;
  }
  
  /**
   * Parse all properties specified for this election and build the election instance.
   * @param the_properties the properties that specify the election.
   * @return a new instance of Election configured per the passed properties.
   */
  @Pure private Election parseProperties(final Properties the_properties) {
    Election result = null;
    try {
      my_vote_timeout = Long.parseLong(the_properties.getProperty("vote_timeout"));
    } catch (final NumberFormatException e) {
      // ignored
    }
    try {
      my_checkpoint_timeout = Long.parseLong(the_properties.getProperty("checkpoint_timeout"));
    } catch (final NumberFormatException e) {
      // ignored
    }
    if (the_properties.containsKey("template_group")) {
      StringTemplateUtil.setTemplateGroup(the_properties.getProperty("template_group"));
      LOGGER.info("Using '" + the_properties.getProperty("template_group") + 
                  "' as template group.");
    }
    if (the_properties.containsKey("checkpoint_file")) {
      my_checkpoint_file = the_properties.getProperty("checkpoint_file");
    }
    // if there is an existing checkpoint of the election, read it and use it
    if (my_checkpoint_file != null) {
      final File cf = new File(my_checkpoint_file);
      if (cf.isFile()) {
        // try to read an Election object from the file
        try {
          final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cf));
          final Election checkpoint = (Election) ois.readObject();
          ois.close();
          result = checkpoint;
          LOGGER.info("read checkpoint file at " + my_checkpoint_file);
        } catch (final IOException | ClassNotFoundException | ClassCastException e) {
          LOGGER.error("could not restore from checkpoint file at " +
                       my_checkpoint_file + ": " + e);
        }
      } else {
        LOGGER.info("no checkpoint file at " + my_checkpoint_file);
      }
    } else {
      LOGGER.info("no checkpoint file specified");
    }
    if (result == null) {
      final StringTokenizer st_voting_systems = 
          new StringTokenizer(the_properties.getProperty("voting_systems"), ",");
      final StringTokenizer st_candidates = 
          new StringTokenizer(the_properties.getProperty("candidates"), ",");
      final List<VotingSystem> voting_systems = new ArrayList<VotingSystem>();
      while (st_voting_systems.hasMoreTokens()) {
        try {
          final String voting_system_name = st_voting_systems.nextToken();
          final Class voting_system_class = Class.forName(voting_system_name);
          final VotingSystem voting_system =
              (VotingSystem) voting_system_class.newInstance();
          voting_systems.add(voting_system);
        } catch (final ClassCastException | ClassNotFoundException | 
                       IllegalAccessException | InstantiationException e) {
          // ignoring malformed classnames
        }
      }
      final List<String> candidates = new ArrayList<String>();
      while (st_candidates.hasMoreTokens()) {
        candidates.add(st_candidates.nextToken());
      }
      result = new Election(the_properties.getProperty("name"),
                            the_properties.getProperty("date"),
                            the_properties.getProperty("description"),
                            voting_systems,
                            candidates,
                            new ConcurrentLinkedQueue<VoterAction>());
      for (VotingSystem vs: result.my_voting_systems) {
        vs.setElection(result);
        vs.setQueue(result.my_queue);
      }
    }
    return result;
  }

  /**
   * Checkpoints the state to the checkpoint file if the required time has elapsed from the 
   * last checkpoint.
   */
  private synchronized void checkpoint() {
    if (my_checkpoint_file != null &&
        System.currentTimeMillis() - my_last_checkpoint_time > my_checkpoint_timeout) {
      final File cf = new File(my_checkpoint_file);
      if (!cf.exists()) {
        try {
          cf.createNewFile();
        } catch (final IOException e) {
          LOGGER.error("unable to create checkpoint file " + my_checkpoint_file + ": " + e);
        }
      }
      if (cf.canWrite()) {
        try {
          final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cf));
          oos.writeObject(my_election);
          oos.close();
          my_last_checkpoint_time = System.currentTimeMillis();
          LOGGER.info("wrote checkpoint to " + my_checkpoint_file);
        } catch (final IOException e) {
          LOGGER.error("could not write checkpoint to " + my_checkpoint_file + ": " + e);
        }
      }
    }
  }
  
  /**
   * Handles an HTTP request for the root page.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The response data.
   */
  private String rootPage(final Request the_request, final Response the_response) {
    // no adversary action in a while, let's cast a queued ballot
    VoterAction va = my_voter_action_queue.peek();
    while (va != null && System.currentTimeMillis() - va.getTimestamp() > my_vote_timeout) {
      // note that this will cast _a_ vote if one is left on the queue, not necessarily
      // _the_ vote that had the good timeout, if it's already been consumed
      va = my_voter_action_queue.poll(); 
      if (va != null) {
        my_election.recordVoterAction(va);
      }
      va = my_voter_action_queue.peek();
    }
    checkpoint();
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "60");
    final ST dashboard_template = StringTemplateUtil.loadTemplate("landing");
    dashboard_template.add("election", my_election);
    page_template.add("body", dashboard_template.render());
    return page_template.render();
  }

  /**
   * Starts an election, including starting up the web server.
   */
  public void start() {
    LOGGER.info("starting election with properties: " + my_properties);
    int port_number = DEFAULT_PORT;
    try {
      final int prop_port_number =
          Integer.parseInt(my_properties.getProperty("port", String.valueOf(port_number)));
      if (MIN_PORT <= prop_port_number && prop_port_number < MAX_PORT) {
        port_number = prop_port_number;
      } else {
        LOGGER.info("invalid port number in properties, using default port");
      }
    } catch (final NumberFormatException e) {
      LOGGER.info("could not read port number from properties, using default port");
    }
    Spark.port(port_number);
    // static files
    Spark.staticFileLocation("/us/freeandfair/es101/static");
    // the top-level for the whole application
    Spark.get("/", (the_request, the_response) -> rootPage(the_request, the_response));
    // register the top-level voting system choice UI
    Spark.get(my_election.my_voting_system_choice.getSchema(), (the_request, the_response) ->
              my_election.my_voting_system_choice.action(the_request, the_response));
    // for every voting system choice, create a callback for their schema and UI
    my_election.my_voting_systems.iterator().forEachRemaining(vs -> 
        Spark.get(vs.getSchema(), (the_request, the_response) -> 
                  vs.action(the_request, the_response)));
    // register the top-level adversary UI
    Spark.get(my_election.my_adversary.getSchema(), (the_request, the_response) ->
              my_election.my_adversary.action(the_request, the_response));
    // register the top-level manipulation UI
    Spark.get(my_election.my_manipulation.getSchema(), (the_request, the_response) -> 
              my_election.my_manipulation.action(the_request, the_response));
  }
  
  /**
   * Creates a default set of properties.
   * 
   * @return The default properties.
   */
  private static Properties defaultProperties() {
    final Properties properties = new Properties();
    try {
      properties.load(ClassLoader.getSystemResourceAsStream(DEFAULT_PROPERTIES));
    } catch (final IOException e) {
      throw new RuntimeException("Error loading default properties file, aborting.");
    }
    return properties;
  }

  /**
   * The main method. Starts the web server and an election using the specified
   * properties file.
   * 
   * @param the_args Command line arguments. Only the first one is considered,
   *          and it is interpreted as the path to a properties file. If no
   *          arguments are supplied, a default election is started.
   */
  public static void main(final String... the_args) {
    Properties properties;
    if (the_args.length > 0) {
      final File file = new File(the_args[0]);
      try {
        LOGGER.info("attempting to load properties from " + file);
        properties = new Properties();
        properties.load(new FileInputStream(file));
      } catch (final IOException e) {
        // could not load properties that way, let's try XML
        try {
          LOGGER.info("load failed, attempting to load XML properties from " + file);
          properties = new Properties();
          properties.loadFromXML(new FileInputStream(file));
        } catch (final IOException ex) {
          // could not load properties that way either, let's use defaults
          LOGGER.info("load failed, loading default properties");
          properties = defaultProperties();
        }
      }
    } else {
      LOGGER.info("no property file specified, loading default properties");
      properties = defaultProperties();
    }

    final Main main = new Main(properties);
    main.start();
  }

}
