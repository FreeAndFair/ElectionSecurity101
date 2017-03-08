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
 * A voting system that uses a digital system to automatically interpret voter intent 
 * from marked paper ballots.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class OpticalScan extends VotingSystem {
  @Pure @Override
  public String getSchema() {
    return "/optical_scan";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("optical_scan").render();
  }

  @Pure @Override
  public String getName() {
    return "Optical Scan";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "All voting in Montana, North and South Dakota, Nebraska, " +
           "New Mexico, Oklahoma, Iowa, Minnesota, Michigan, New York, Alabama, Maryland, " +
           "Maine, New Hampshire, Vermont, Connecticut, Rhode Island, Maryland, and " + 
           "Washington D.C., Washington, Colorado, and Oregon uses optical scan systems.";
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
    return true;
  }

  @Pure @Override
  public boolean isVoteVisible() {
    return true;
  }
}
