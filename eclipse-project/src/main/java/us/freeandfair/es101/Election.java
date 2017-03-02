/**
 * Free & Fair Election Security 101 System
 * 
 * @title ElectionSecurity101
 * @created Mar 1, 2017
 * @copyright 2017 Free & Fair
 * @license BSD 3-Clause License
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @description A web-based demonstration that illustrates the relative security
 *              of various voting methods in common use in the United States.
 */

package us.freeandfair.es101;

import java.util.Collection;
import java.util.List;

/**
 * A formal indication of choices in one or more contests.
 *  
 * @version 1.0
 * @author Joseph R. Kiniry
 * @author Daniel M. Zimmerman
 */
public class Election {
  /** The voter. */
  private Voter my_voter;
  
  /** The adversary. */
  private Adversary my_adversary;
  
  /** The voting systems in use. */
  private Collection<VotingSystem> my_voting_systems;
  
  /** The votes that have been cast. */
  private List<Vote> my_votes;
  
  /** The tally. */
  private Tally my_tally;
}
