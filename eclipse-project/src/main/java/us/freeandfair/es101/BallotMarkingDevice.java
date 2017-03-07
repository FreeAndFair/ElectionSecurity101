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

/**
 * A system which permits voters to indicate their contest choices and then 
 * captures those voter choices on paper ballots.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class BallotMarkingDevice extends VotingSystem {
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#schema()
   */
  @Pure @Override
  public String getSchema() {
    return "/ballot-marking-device";
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
}
