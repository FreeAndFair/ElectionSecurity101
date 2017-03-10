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

import us.freeandfair.es101.util.StringTemplateUtil;

/**
 * An election carried out, in whole or in part, using the Internet
 * for the transmission of votes.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class InternetElection extends VotingSystem {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Pure @Override
  public String getSchema() {
    return "/internet_election";
  }
  
  @Pure @Override
  public String getAfterVotingText() {
    return super.getAfterVotingText() +
        "You just gave your vote to the bad guys!";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("internet_election").render();
  }

  @Pure @Override
  public String getName() {
    return "Internet";
  }

  @Pure @Override
  public String getReceipt(final VoterAction the_voter_action) {
    return "We really really promise that we recorded your vote properly in our server logs.";
  }

  @Pure @Override
  public String getReceiptName() {
    return "Server Log Entry";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "This kind of voting system has been experimented with and rejected by " +
           "numerous governments around the world, but is being used in around two dozen " +
           "states in the U.S.A. to collect votes from overseas Americans and military " + 
           "personnel.  In AK, any citizen can vote over the Internet!";
  }

  @Pure @Override
  public boolean isReceiptGenerated() {
    return true;
  }

  @Pure @Override
  public boolean isReceiptManipulable() {
    return true;
  }

  @Pure @Override
  public boolean isVoteManipulable() {
    return true;
  }

  @Pure @Override
  public boolean isVoteVisible() {
    return true;
  }
}
