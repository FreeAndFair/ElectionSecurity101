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
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Pure @Override
  public String getSchema() {
    return "/dre";
  }
  
  @Pure @Override
  public String getAfterVotingText() {
    return super.getAfterVotingText() +
        "You just gave your vote to the bad guys!";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("dre").render();
  }

  @Pure @Override
  public String getAfterManipulationText() {
    return "Your hack might be detected if there is a paper trail and " +
           "officials check the results against it.";
  }
  
  @Pure @Override
  public String getName() {
    return "Direct Recording Electronic (DRE)";
  }

  @Pure @Override
  public String getReceipt(final VoterAction the_voter_action) {
    return "We really really promise that we recorded your vote in our DRE logs.";
  }

  @Pure @Override
  public String getReceiptName() {
    return "DRE Log Entry";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "All voting in DE, GA, LA, NJ, NV, SC, UT is on DREs. " + 
           "Much of the voting in AK, AZ, CA, FL, ID, IL, IN, HI, KY, " +
           "MO, NC, OH, PA, TN, TX, WI, WV, WY, and VA is on DREs.  Some " +
           "voting in AR, KS and MS is on DREs.";
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
