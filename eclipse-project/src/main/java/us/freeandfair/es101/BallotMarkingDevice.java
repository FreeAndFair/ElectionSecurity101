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
 * A system which permits voters to indicate their contest choices and then 
 * captures those voter choices on paper ballots.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class BallotMarkingDevice extends VotingSystem {

  @Pure @Override
  public String getSchema() {
    return "/ballot-marking-device";
  }
  
  @Pure @Override
  public String getAfterVotingText() {
    return super.getAfterVotingText() +
        "\nAt least your vote is guaranteed to be filled out correctly!";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("ballot_marking_device").render();
  }

  @Pure @Override
  public String getName() {
    return "Ballot Marking Device";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "This kind of voting system has been experimented with and rejected by " +
           "numerous governments around the world, but is being used in around two dozen " +
           "states in the U.S.A. to collect votes from overseas Americans and military " + 
           "personnel.  And in Alaska, any citizen can vote over the internet!";
  }

  @Pure @Override
  public boolean isReceiptGenerated() {
    return false;
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
