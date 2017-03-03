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

/**
 * @description All scenarios of the ES101 system.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Scenarios {
  /** The election related to these scenarios. */
  private Election my_election;

  /**
   * The hand-marked and hand-counted scenario. 
   * @scenario Hand-marked and counted voting:
   *             Voter chooses to vote using the hand count method.
   *             A blank ballot is printed.
   *             Voter votes in the election.
   *             Voter hands the ballot to adversary.
   *             The vote cannot be manipulated.
   *             The vote is added to the legitimate and hacked tallies.
   *             The ballot is inserted into the ballot box.
   */
  public void handMarkedAndCounted() {
    my_election.my_voting_system_choice.display();
    final String voting_system_choice = 
        my_election.my_voting_system_choice.action(null, null);
    assert "HAND-MARKED".equals(voting_system_choice);
    final Vote vote = my_election.my_voter.vote();
    my_election.my_adversary.my_ui.display();
    my_election.my_adversary.noManipulation();
    my_election.my_tally.addUnmanipulatedVote(vote);
    assert false;
    //@ assert false;
  }

}

