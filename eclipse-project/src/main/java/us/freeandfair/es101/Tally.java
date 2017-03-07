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
import java.util.Map;
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
  public void addUnmanipulatedVote(final String the_vote) {
    if (my_legitimate_tally.containsKey(the_vote) &&
        my_hacked_tally.containsKey(the_vote)) {
      my_legitimate_tally.put(the_vote, my_legitimate_tally.get(the_vote) + 1);
      my_hacked_tally.put(the_vote, my_hacked_tally.get(the_vote) + 1);
    }
  }
  
  /**
   * Add a vote to the tally.
   * 
   * @param the_original_vote The vote.
   * @param the_manipulated_vote The manipulated vote.
   */
  public void addVote(final String the_original_vote, final String the_manipulated_vote) {
    if (my_legitimate_tally.containsKey(the_original_vote) &&
        my_hacked_tally.containsKey(the_manipulated_vote)) {
      my_legitimate_tally.put(the_original_vote, 
                              my_legitimate_tally.get(the_original_vote) + 1);
      my_hacked_tally.put(the_manipulated_vote, 
                          my_hacked_tally.get(the_manipulated_vote) + 1);
    }
  }
  
  /**
   * @return the legitimate tally.
   */
  public Map<String, Integer> getLegitimateTally() {
    return my_legitimate_tally; // note not worrying about ownership
  }

  /**
   * @return the hacked tally.
   */
  public Map<String, Integer> getHackedTally() {
    return my_hacked_tally; // note not worrying about ownership
  }
}
