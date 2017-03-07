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
    return "End-to-End Verifiable Election";
  }

  @Pure @Override
  public String getReceipt(final VoterAction the_voter_action) {
    return Integer.toHexString(super.getReceipt(the_voter_action).hashCode());
  }

  @Pure @Override
  public String getReceiptName() {
    return "cryptographic receipt";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "System like this have been experimented with in Tacoma Park, MD and " +
      "Austin, TX is about to start working on such a system and intends to make it " +
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

  @Pure @Override
  protected ST iVotedPageSetup(final String the_vote) {
    final ST i_voted_template = super.iVotedPageSetup(the_vote);
    i_voted_template.add("message", 
                         "Don't forget to check your receipt after the election to ensure " +
                         "that your vote was included in the election results!");
    return i_voted_template;
  }
}
