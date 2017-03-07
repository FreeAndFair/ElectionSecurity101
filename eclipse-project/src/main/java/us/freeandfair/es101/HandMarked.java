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
 * A paper ballot that is hand-marked by a voter to capture their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class HandMarked extends VotingSystem {  
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#schema()
   */
  @Pure @Override
  public String getSchema() {
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
   * @see us.freeandfair.es101.VotingSystem#isReceiptManipulable()
   */
  @Pure @Override
  public boolean isReceiptManipulable() {
    return false;
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#isVoteManipulable()
   */
  @Pure @Override
  public boolean isVoteManipulable() {
    return false;
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#getName()
   */
  @Pure @Override
  public String getName() {
    return "Hand-Marked!";
  }
  
  @Pure @Override
  public String getUsageRegions() {
    return "New Hampshire and Idaho";
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#action(spark.Request, spark.Response)
   */
  @Pure @Override
  public String action(final Request the_request, final Response the_response) {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.action(the_request, the_response);
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.VotingSystem#isVoteVisible()
   */
  @Pure @Override
  public boolean isVoteVisible() {
    return true;
  }
}
