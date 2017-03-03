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

import java.util.Collection;
import java.util.List;

import org.jmlspecs.annotation.Pure;

/**
 * A formal indication of choices in one or more contests.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Election {
  protected String my_name;
  protected String my_current_date;
  
  /** The UI for choosing a voting system. */
  protected UserInterface my_voting_system_choice;
  
  /** The voter. */
  protected Voter my_voter;
  
  /** The adversary. */
  protected Adversary my_adversary;
  
  /** The voting systems in use. */
  protected Collection<VotingSystem> my_voting_systems;
  
  /** The votes that have been cast. */
  protected List<Vote> my_votes;
  
  /** The tally. */
  protected Tally my_tally;
}
