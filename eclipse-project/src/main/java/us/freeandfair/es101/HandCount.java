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

import java.util.Collection;

/**
 * A voting system using no digital assistance in which all votes are manually counted.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class HandCount extends Tally {

  /**
   * @todo <description>
   * @todo <explanation>
   * @param the_candidates @todo
   */
  public HandCount(final Collection<String> the_candidates) {
    super(the_candidates);
    // todo Auto-generated constructor stub
    assert false;
    //@ assert false;
  }

}
