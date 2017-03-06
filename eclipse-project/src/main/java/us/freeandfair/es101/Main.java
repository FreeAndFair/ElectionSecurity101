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
import java.io.IOException;
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

import us.freeandfair.es101.util.StringTemplateUtil;

import static spark.Spark.get;
import static spark.Spark.port;

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
  private final Queue<VoterAction> my_voter_action_queue = 
      new ConcurrentLinkedQueue<VoterAction>();

  /**
   * The election we're running.
   */
  private final Election my_election;
  
  /**
   * Constructs a new Main with the specified properties.
   * 
   * @param the_properties The properties.
   */
  public Main(final Properties the_properties) {
    my_properties = the_properties;
    my_election = parseProperties(the_properties);
  }
  
  /**
   * Parse all properties specified for this election and build the election instance.
   * @param the_properties the properties that specify the election.
   * @return a new instance of Election configured per the passed properties.
   */
  @Pure private Election parseProperties(final Properties the_properties) {
    final StringTokenizer st_voting_systems = 
        new StringTokenizer(the_properties.getProperty("voting_systems"), ",");
    final StringTokenizer st_candidates = 
        new StringTokenizer(the_properties.getProperty("candidates"), ",");
    final List<VotingSystem> voting_systems = new ArrayList<VotingSystem>();
    while (st_voting_systems.hasMoreTokens()) {
      try {
        String votingSystemName = st_voting_systems.nextToken();
        Class votingSystemClass = Class.forName(votingSystemName);
        VotingSystem votingSystem = (VotingSystem)votingSystemClass.newInstance();
        voting_systems.add(votingSystem);
      } catch (final ClassCastException | ClassNotFoundException | IllegalAccessException |
                     InstantiationException e) {
        // ignoring malformed classnames
      }
    }
    final List<String> candidates = new ArrayList<String>();
    while (st_candidates.hasMoreTokens()) {
      candidates.add(st_candidates.nextToken());
    }
    Election result = new Election(the_properties.getProperty("name"),
                                   the_properties.getProperty("date"),
                                   voting_systems,
                                   candidates,
                                   my_voter_action_queue);
    for (VotingSystem vs: voting_systems) {
      vs.setElection(result);
    }
    return result;
  }

  /**
   * Handles an HTTP request for the root page.
   * 
   * @return The response data.
   */
  private String rootPage() {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", true);
    page_template.add("results", "<h2 align=\"center\">No Results Yet</h2>");
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "30");
    page_template.
        add("body", "Welcome to Free & Fair Election Security 101. " +
                    "Are you a <a href=\"voting_system_choice\">voter</a> or an " +
                    "<a href=\"/adversary\">adversary</a>?");
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
    port(port_number);
    // the top-level for the whole application
    get("/", (the_request, the_response) -> rootPage());
        // register the top-level voting system choice UI
    get(my_election.my_voting_system_choice.getSchema(), (the_request, the_response) ->
        my_election.my_voting_system_choice.action(the_request, the_response));
    // for every voting system choice, create a callback for their schema and UI
    my_election.my_voting_systems.iterator().forEachRemaining(vs -> 
        get(vs.getSchema(), (the_request, the_response) -> 
                         vs.action(the_request, the_response)));
    // register the top-level adversary UI
    get(my_election.my_adversary.getSchema(), (the_request, the_response) ->
        my_election.my_adversary.action(the_request, the_response));
    // register the top-level manipulation UI
    get(my_election.my_manipulation.getSchema(),
        (the_req, the_resp) -> my_election.my_manipulation.action(the_req, the_resp));
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
