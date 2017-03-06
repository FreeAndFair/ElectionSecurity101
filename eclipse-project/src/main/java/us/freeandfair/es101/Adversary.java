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

import spark.Request;
import spark.Response;

/**
 * A person or entity that wishes to manipulate the outcome of an election.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Adversary extends UserInterface {
  /** The vote cannot be manipulated. */
  @Pure public void noManipulation() {
    assert false;
    //@ assert false;
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#schema()
   */
  @Pure @Override
  public String schema() {
    return "adversary";
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#action(spark.Request, spark.Response)
   */
  @Pure @Override
  public String action(Request the_request, Response the_response) {
    // todo Auto-generated method stub
    assert false;
    //@ assert false;
    return super.action(the_request, the_response);
  }
}
