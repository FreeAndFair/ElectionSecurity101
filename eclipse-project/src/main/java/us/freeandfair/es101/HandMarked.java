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
 * A paper ballot that is hand-marked by a voter to capture their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class HandMarked extends VotingSystem {
  public String schema = "/hand-marked";
  public String name = "Hand Marked Ballot";
  public String usage_regions = "Usage Regions";
  
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
