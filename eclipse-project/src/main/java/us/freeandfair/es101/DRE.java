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
 * A direct recording electronic voting system which has no VVPAT.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class DRE extends VotingSystem {
  @Pure @Override
  public String getAfterVotingText() {
    return super.getAfterVotingText() +
        "\nYou just gave your vote to the bad guys!";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("dre").render();
  }

  @Pure @Override
  public String getName() {
    return "Direct Recording Electronic (DRE) Voting Machines";
  }

  @Pure @Override
  public String getReceipt(final VoterAction the_voter_action) {
    return "We really really promise that we recorded that you voted in our DRE logs.";
  }

  @Pure @Override
  public String getReceiptName() {
    return "DRE log entry";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "All voting in Louisiana, Georgia, South Carolina, New Jersey, and " +
           "Delaware is on DREs.  Much of the voting that happens in Texas, Florida, " +
           "Tennessee, Kentucky, Indiana, Virginia, and Pennsylvania is on DREs.  Some " +
           "voting in Kansas, Arkansas, and Mississippi is on DREs.";
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
