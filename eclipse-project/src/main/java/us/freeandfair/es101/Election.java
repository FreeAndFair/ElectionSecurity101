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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import org.jmlspecs.annotation.Pure;

/**
 * A formal indication of choices in one or more contests.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Election {
  /** The name of this election. */
  protected String my_name;
  
  /** The date of this election. */
  protected String my_date;
  
  /** The UI for choosing a voting system. */
  protected UserInterface my_voting_system_choice;

  /** The adversary. */
  protected Adversary my_adversary;
  
  /** The manipulation. */
  protected Manipulation my_manipulation;
  
  /** The voting systems in use. */
  protected Collection<VotingSystem> my_voting_systems;
  
  /** The candidates. */
  protected Collection<String> my_candidates;
  
  /** The tally. */
  protected Tally my_tally;
  
  /** The number of votes that were manipulated. */
  protected long my_vote_manipulation_count;
  
  /** The number of receipts that were manipulated. */
  protected long my_receipt_manipulation_count;
  
  /**
   * Create and initialize a new instance of Election.
   * @param the_name the name of this election.
   * @param the_date the date this election begins.
   * @param the_voting_systems the voting systems supported in this election.
   * @param the_candidates vying for a seat in this election.
   * @param the_queue the queue of voter actions that have taken place.
   */
  @Pure
  public Election(final String the_name,
                  final String the_date,
                  final Collection<VotingSystem> the_voting_systems,
                  final Collection<String> the_candidates,
                  final Queue<VoterAction> the_queue) {
    my_name = the_name;
    my_date = the_date;
    my_voting_system_choice = new VotingSystemChoice(this, the_queue);
    my_voting_systems = the_voting_systems;
    my_candidates = the_candidates;
    my_tally = new Tally(my_candidates);
    my_adversary = new Adversary(this, the_queue);
    my_manipulation = new Manipulation(this, the_queue);
  }

  /**
   * @return The tally for this election.
   */
  public Tally getTally() {
    return my_tally;
  }
  
  /**
   * @return the collection of voting systems in use for this election.
   */
  public Collection<VotingSystem> getVotingSystems() {
    return my_voting_systems;
  }
  
  /**
   * @return the collection of candidates for this election.
   */
  public Collection<String> getCandidates() {
    return my_candidates;
  }
  
  /**
   * Record a voter action; this records the vote in the tally, and stores the
   * voter action for logging purposes.
   */
  public synchronized void recordVoterAction(final VoterAction the_voting_action) {
    if (the_voting_action.isVoteManipulated()) {
      my_vote_manipulation_count = my_vote_manipulation_count + 1;
      my_tally.addVote(the_voting_action.my_vote, the_voting_action.my_manipulated_vote);
    } else {
      my_tally.addUnmanipulatedVote(the_voting_action.my_vote);
    }
  }
}
