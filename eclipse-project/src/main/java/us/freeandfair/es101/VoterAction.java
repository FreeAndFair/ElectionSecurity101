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
 * Details about an action taken by the voter that is awaiting adversary interaction.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VoterAction {
  /**
   * The voting system that the voter used.
   */
  protected VotingSystem my_voting_system;
  
  /**
   * The vote that the voter cast.
   */
  protected Vote my_vote;
  
  /**
   * The vote after manipulation by the adversary.
   */
  protected Vote my_manipulated_vote;
}
