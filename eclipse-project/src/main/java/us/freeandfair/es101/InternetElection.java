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

/**
 * An election carried out, in whole or in part, using the Internet
 * for the transmission of votes.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class InternetElection extends VotingSystem {
  /**
   * Create a internet voting system for an election.
   * @param the_election the election for which this voting system is in use.
   */
  @Pure
  public InternetElection(final Election the_election) {
    super(the_election);
  }

}
