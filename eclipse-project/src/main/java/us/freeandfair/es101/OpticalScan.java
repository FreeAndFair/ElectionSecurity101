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
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Pure @Override
  public String getSchema() {
    return "/optical_scan";
  }

  @Pure @Override
  public String getExplanationText() {
    return StringTemplateUtil.loadTemplate("optical_scan").render();
  }

  @Pure @Override
  public String getAfterManipulationText() {
    return "Your hack might be detected if officials check the " +
           "results against the paper ballots.";
  }
  
  @Pure @Override
  public String getName() {
    return "Optical Scan";
  }

  @Pure @Override
  public String getUsageRegions() {
    return "All voting in AL, CO, CT, DC, IA, MD, ME, MI, MN, MT, ND, " +
           "NE, NH, NM, NY, OK, OR, RI, SD, VT, and WA uses optical " +
           "scan systems.";
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
