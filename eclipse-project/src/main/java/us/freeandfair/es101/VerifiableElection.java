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
 * An election scheme whereby one or more formal properties of the election can be 
 * independently checked by voters or election officials.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VerifiableElection extends VotingSystem {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Pure @Override
  public String getSchema() {
    return "/e2e_verifiable";
  }
  
  @Pure @Override
  public String getAfterVotingText() {
    return super.getAfterVotingText() +
        "\nDo not forget to use your receipt to check that your vote was counted correctly!";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("verifiable_election").render();
  }

  @Pure @Override
  public String getName() {
    return "End-to-End Verifiable";
  }

  @Pure @Override
  public String getReceipt(final VoterAction the_voter_action) {
    return Integer.toHexString(super.getReceipt(the_voter_action).hashCode());
  }

  @Pure @Override
  public String getReceiptName() {
    return "Cryptographic Receipt";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "Takoma Park, MD has experimented with and end-to-end verifiable election system. " +
      "Travis County, TX (Austin) is working on such a system and intends to make it " +
      "available to the entire U.S.A.";
  }

  @Pure @Override
  public boolean isReceiptGenerated() {
    return true;
  }

  @Pure @Override
  public boolean isReceiptManipulable() {
    return false;
  }

  @Pure @Override
  public boolean isVoteManipulable() {
    return false;
  }

  @Pure @Override
  public boolean isVoteVisible() {
    return false;
  }
}
