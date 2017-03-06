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

import org.jmlspecs.annotation.Nullable;
import org.jmlspecs.annotation.Pure;

import spark.Request;
import spark.Response;

/**
 * The digital medium through which a voter or electoral authority interacts with a 
 * digital voting system.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class UserInterface {
  /** The election associated with this UI. */
  protected Election my_election;
  
  /**
   * Create a new UI for the election.
   * @param the_election the election for this UI.
   */
  @Pure
  public UserInterface(final Election the_election) {
    my_election = the_election;
  }
  
  /**
   * @return the URL schema prefix to which this UI responds.  This base 
   * abstract class returns an empty string.
   */
  //@ public normal_behavior ensures \result.equals("");
  @Pure public String schema() {
    return "";
  }

  /**
   * @return A string which is displayed to the voter as the UI.
   * @param the_request The request object providing information about the HTTP request
   * @param the_response The response object providing functionality for modifying the response
   */
  //@ public normal_behavior ensures \result.equals("");
  @Pure public String action(final Request the_request, final Response the_response) {
    return "";
  }
}
