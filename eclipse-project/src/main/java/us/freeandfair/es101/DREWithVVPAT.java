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
 * A direct recording electronic voting system which has a VVPAT.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class DREWithVVPAT extends VotingSystem {
  @Pure @Override
  public String getAfterVotingText() {
    return super.getAfterVotingText() +
        "\nYou may have just given your vote to the bad guys!  Did you triple-check your " +
        "VVPAT?  Do you not know what that means?  Well, too bad!";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("dre_with_vvpat").render();
  }

  @Pure @Override
  public String getName() {
    return "Direct Recording Electronic (DRE) Voting Machines with a Voter-Verifiable " +
           "Paper Audit Trail (VVPAT)";
  }

  @Pure @Override
  public String getReceipt(final VoterAction the_voter_action) {
    return "===afw49778hfiuanf4i8ahfaufg48wf4a===\n" +
           the_voter_action.my_manipulated_receipt +
           "===92f8h4ounfh8sof42n28oj24oflsjf8===";
  }

  @Pure @Override
  public String getReceiptName() {
    return "VVPAT";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "All voting in Utah and Nevada is on DREs with VVPAT.  Much of the voting that " +
           "happens in California, Idaho, Alaska, Hawaii, Wyoming, Arizona, Missouri, " +
           "Illinois, Wisconsin, Ohio, West Virginia, and North Carolina is on DREs with " +
           "VVPAT. Some of the voting in Kansas, Arkansas, and Mississippi is on DREs with " +
           "VVPAT.";
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
