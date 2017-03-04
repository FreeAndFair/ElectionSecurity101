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
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The count of all the votes in an election.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Tally {
  /**
   * The user interface that displays the tally.
   */
  protected UserInterface my_ui;
  
  /**
   * The legitimate tally values.
   */
  protected final SortedMap<String, Integer> my_legitimate_tally;
  
  /**
   * The hacked tally values.
   */
  protected final SortedMap<String, Integer> my_hacked_tally;
 
  /**
   * Construct a new, zeroed Tally.
   * 
   * @param the_candidates The candidates.
   */
  public Tally(final Collection<String> the_candidates) {
    my_legitimate_tally = new TreeMap<String, Integer>();
    
    for (final String c : the_candidates) {
      my_legitimate_tally.put(c, 0);
    }
    
    my_hacked_tally = new TreeMap<String, Integer>(my_legitimate_tally);
  }
  
  /**
   * Add an unmanipulated vote to the tally.
   * 
   * @param the_vote The vote.
   */
  public void addUnmanipulatedVote(final Vote the_vote) {
    assert false;
    //@ assert false;
  }
  
  /**
   * Add a vote to the tally.
   * 
   * @param the_original_vote The vote.
   * @param the_manipulated_vote The manipulated vote.
   */
  public void addVote(final Vote the_original_vote, final Vote the_manipulated_vote) {
    assert false;
    //@ assert false;
  }
}
