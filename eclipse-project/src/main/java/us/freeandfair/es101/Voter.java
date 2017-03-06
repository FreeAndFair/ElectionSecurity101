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
 * A person who is eligible to vote in an election.
 * 
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Voter {
  /**
   * The voting system chosen by the voter.
   */
  protected VotingSystem my_voting_system;
  
  /**
   * @return the choice the voter makes in the election.
   */
  @Pure public Vote vote() {
    assert false;
    //@ assert false;
    my_voting_system.action(null, null);
    return null;
  }
  
}
