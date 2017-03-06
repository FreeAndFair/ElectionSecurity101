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
 * The operational means by which a voter captures their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VotingSystem extends UserInterface {
  /** The election for which this voting system is in use. */
  protected Election my_election;
  
  /**
   * Create a new voting system for an election.
   * @param the_election the election for which this voting system is in use.
   */
  @Pure
  public VotingSystem(final Election the_election) {
    my_election = the_election;
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#action(spark.Request, spark.Response)
   */
  @Pure @Override
  public String action(final Request the_request, final Response the_response) {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.action(the_request, the_response);
  }  
}
