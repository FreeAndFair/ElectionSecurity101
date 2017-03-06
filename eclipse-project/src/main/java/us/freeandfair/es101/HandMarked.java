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
import org.stringtemplate.v4.ST;

import spark.Request;
import spark.Response;

/**
 * A paper ballot that is hand-marked by a voter to capture their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class HandMarked extends VotingSystem {
  /**
   * Create a hand-marked and hand-counted voting system for an election.
   * @param the_election the election for which this voting system is in use.
   */
  @Pure
  public HandMarked(final Election the_election) {
    super(the_election);
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#schema()
   */
  @Pure @Override
  public String schema() {
    return "/hand-marked";
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#explanationText()
   */
  @Pure @Override
  protected String explanationText() {
    return "A hand-marked ballot!";
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#canManipulateReceipt()
   */
  @Pure @Override
  public boolean canManipulateReceipt() {
    return false;
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#canManipulateVote()
   */
  @Pure @Override
  public boolean canManipulateVote() {
    return false;
  }
}
