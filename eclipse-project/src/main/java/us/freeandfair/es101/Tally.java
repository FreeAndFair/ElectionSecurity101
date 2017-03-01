/**
 * Free & Fair Election Security 101 System
 * 
 * @title ElectionSecurity101
 * @created Mar 1, 2017
 * @copyright 2017 Free & Fair
 * @license BSD 3-Clause License
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @description A web-based demonstration that illustrates the relative security
 *              of various voting methods in common use in the United States.
 */

package us.freeandfair.es101;

/**
 * @description The count of all the votes in an election.
 */
public class Tally {
  UserInterface my_ui;
  int my_legitimate_tally;
  int my_hacked_tally;
  // The unmanipulated vote is added to the tally.
}
