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

import java.io.IOException;
import java.io.Serializable;

/**
 * Details about an action taken by the voter that is awaiting adversary interaction.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VoterAction implements Serializable {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The next unique ID to be assigned.
   */
  private static long NEXT_ID = 0L;
      
  /**
   * The voting system that the voter used.
   */
  protected VotingSystem my_voting_system;
  
  /**
   * The vote that the voter cast.
   */
  protected String my_vote;
  
  /**
   * The vote after manipulation by the adversary.
   */
  protected String my_manipulated_vote;
  
  /**
   * The receipt after manipulation by the adversary.
   */
  protected String my_manipulated_receipt;
  
  /**
   * The unique ID of this voter action.
   */
  protected final long my_id;
  
  /**
   * The time when this voter action was last touched.
   */
  protected long my_timestamp;
  
  /**
   * Construct a new VoterAction with the specified voting system and vote. The 
   * specified vote is used as the vote that the voter cast, and is also used as
   * the initial setting for the manipulated vote and manipulated receipt.
   * 
   * @param the_voting_system The voting system.
   * @param the_vote The vote.
   */
  public VoterAction(final VotingSystem the_voting_system, final String the_vote) {
    my_id = getNextID();
    my_vote = the_vote;
    my_manipulated_vote = my_vote;
    my_manipulated_receipt = my_vote;
    my_voting_system = the_voting_system;
    my_timestamp = System.currentTimeMillis();
  }
  
  /**
   * @return The next unique ID.
   */
  private static synchronized long getNextID() {
    final long result = NEXT_ID;
    NEXT_ID = NEXT_ID + 1;
    return result;
  }
  
  /**
   * Reads an object via serialization; this method exists only to update the NEXT_ID
   * static field to be higher than every VoterAction unserialized.
   * 
   * @param the_stream The object input stream.
   */
  private void readObject(java.io.ObjectInputStream the_stream)
      throws IOException, ClassNotFoundException {
    the_stream.defaultReadObject();
    NEXT_ID = Math.max(NEXT_ID, my_id + 1);
  }
  
  /**
   * @return the voting system for this VoterAction.
   */
  public VotingSystem getVotingSystem() {
    return my_voting_system;
  }
  
  /**
   * @return the vote for this VoterAction.
   */
  public String getVote() {
    return my_vote;
  }
  
  /**
   * @return the ID for this VoterAction.
   */
  public long getID() {
    return my_id;
  }
  
  /**
   * @return true if the vote in this VoterAction has been manipulated, false otherwise.
   */
  public boolean isVoteManipulated() {
    return !my_vote.equals(my_manipulated_vote);
  }
 
  /**
   * @return true if the receipt in this VoterAction has been manipulated, false otherwise.
   */
  public boolean isReceiptManipulated() {
    return !my_vote.equals(my_manipulated_receipt);
  }
  
  /**
   * Updates the timestamp of this VoterAction to the current time.
   */
  public void updateTimestamp() {
    my_timestamp = System.currentTimeMillis();
  }
  
  /**
   * @return the timestamp of this VoterAction.
   */
  public long getTimestamp() {
    return my_timestamp;
  }

}
