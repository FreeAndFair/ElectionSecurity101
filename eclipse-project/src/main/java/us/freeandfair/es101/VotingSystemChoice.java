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

import org.jmlspecs.annotation.Pure;

import spark.Request;
import spark.Response;

/**
 * @description <description>
 * @explanation <explanation>
 * @bon OPTIONAL_BON_TYPENAME
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VotingSystemChoice extends UserInterface {
  
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#my_url_schema()
   */
  @Pure @Override
  public String schema() {
    return "voting_system_choice";
  }
  
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#display()
   */
  @Override
  public String display() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.display();
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#action()
   */
  /**
    * Voter chooses to vote.
    */
  @Override
  public String action(final Request the_request, final Response the_response) {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.action(the_request, the_response);
  }
}
