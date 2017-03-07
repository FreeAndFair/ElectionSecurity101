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
import org.stringtemplate.v4.ST;

/**
 * An election scheme whereby one or more formal properties of the election can be 
 * independently checked by voters or election officials.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VerifiableElection extends VotingSystem {
  @Override
  public String getAfterVotingText() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.getAfterVotingText();
  }

  @Override
  public String getExplanationText() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.getExplanationText();
  }

  @Override
  public String getName() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.getName();
  }

  @Override
  public String getReceipt(final VoterAction the_voter_action) {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.getReceipt(the_voter_action);
  }

  @Override
  public String getReceiptName() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.getReceiptName();
  }

  @Override
  public String getUsageRegions() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.getUsageRegions();
  }

  @Override
  public boolean isReceiptGenerated() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.isReceiptGenerated();
  }

  @Override
  public boolean isReceiptManipulable() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.isReceiptManipulable();
  }

  @Override
  public boolean isVoteManipulable() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.isVoteManipulable();
  }

  @Override
  public boolean isVoteVisible() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.isVoteVisible();
  }

  @Override
  protected ST iVotedPageSetup(String the_vote) {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.iVotedPageSetup(the_vote);
  }

  @Override
  protected ST votingSystemPageSetup() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.votingSystemPageSetup();
  }

  @Override
  protected ST votingSystemThankYouPageSetup() {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.votingSystemThankYouPageSetup();
  }
}
