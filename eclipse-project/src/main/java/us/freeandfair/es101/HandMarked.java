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
 * A paper ballot that is hand-marked by a voter to capture their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class HandMarked extends VotingSystem {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("hand_marked").render();
  }

  @Pure @Override
  public String getName() {
    return "Hand-Marked and Hand-Counted";
  }

  @Pure @Override
  public String getSchema() {
    return "/hand_marked";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "A few handfuls of very small counties in New Hampshire, Maine, Georgia, Idaho, " +
           "and elsewhere.";
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
    return true;
  }
}
