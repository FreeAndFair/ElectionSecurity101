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
import java.util.Properties;

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
   * The properties loaded from the properties file.
   */
  private final Properties my_properties;
  
  /**
   * Constructs a new Main with the specified properties.
   * 
   * @param the_properties The properties.
   */
  public Main(final Properties the_properties) {
    my_properties = the_properties;
  }
  
  /**
   * Starts an election, including starting up the web server.
   */
  public void start() {
    
  }
  
  /**
   * Creates a default set of properties.
   * 
   * @return The default properties.
   */
  private static Properties defaultProperties() {
    // will later read this from an internal properties file that is known to be defined
    return new Properties();
  }
  
  /**
   * The main method. Starts the web server and an election using the specified
   * properties file.
   * 
   * @param the_args Command line arguments. Only the first one is considered, 
   *  and it is interpreted as the path to a properties file. If no arguments
   *  are supplied, a default election is started.
   */
  public static void main(final String... the_args) {
    Properties properties = defaultProperties();
    if (the_args.length > 0) {
      final File file = new File(the_args[0]);
      try {
        properties.clear();
        properties.load(new FileInputStream(file));
      } catch (final IOException e) {
        // could not load properties that way, let's try XML
        try {
          properties.clear();
          properties.loadFromXML(new FileInputStream(file));
        } catch (final IOException ex) {
          // could not load properties that way either, let's use defaults
          properties = defaultProperties();
        }
      }
    }
    
    final Main main = new Main(properties);
    main.start();
  }

}
