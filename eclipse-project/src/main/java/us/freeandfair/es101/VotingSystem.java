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

import java.util.Queue;

/**
 * The operational means by which a voter captures their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VotingSystem extends UserInterface {
  /** The voter's user interface of this voting system. */
  protected UserInterface my_voter_ui;
  
  /** The adversary's user interface of this voting system. */
  protected UserInterface my_adversary_ui;
  
  /** The voter action queue on which this voting system can enqueue votes. */
  protected Queue<VoterAction> my_queue;
  
  /** 
   * @return true if an adversary can manipulate the receipt for this system, 
   * false otherwise. 
   * */
  public boolean canManipulateReceipt() {
    return true;
  }
  
  /** 
   * @return true if an adversary can manipulate the vote for this system, 
   * false otherwise. 
   */
  public boolean canManipulateVote() {
    return true;
  }
  
  /**
   * @return true if an adversary can see the vote for this system, 
   * false otherwise.
   */
  public boolean canSeeVote() {
    return true;
  }
}
