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
import org.stringtemplate.v4.ST;

import spark.Request;
import spark.Response;
import us.freeandfair.es101.util.StringTemplateUtil;

import java.util.Queue;

/**
 * The operational means by which a voter captures their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VotingSystem extends UserInterface {
  /** The election for which this voting system is in use. */
  protected Election my_election;
  
  /** The voter action queue on which this voting system can enqueue votes. */
  protected Queue<VoterAction> my_queue;
  
  /**
   * Create a new voting system for an election.
   * @param the_election the election for which this voting system is in use.
   */
  @Pure
  public VotingSystem(final Election the_election) {
    my_election = the_election;
  }
  
  /**
   * @return text that explains this voting system's pros and cons. 
   */
  @Pure
  protected String explanationText() {
    return "";
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#action(spark.Request, spark.Response)
   */
  @Pure @Override
  public String action(final Request the_request, final Response the_response) {
    return votingSystemPageSetup().render();
  }  

  /**
   * Create the general template voting system page from which all voting system
   * subclasses will customize.
   * @return the string template on which to build.
   */
  @Pure
  protected ST votingSystemPageSetup() {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", true);
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "15");
    final ST voting_system_template = StringTemplateUtil.loadTemplate("voting_system");
    voting_system_template.add("election", my_election);
    voting_system_template.add("explanation", explanationText());
    page_template.add("body", voting_system_template.render());
    return page_template;
  }
  
  /** 
   * @return true if an adversary can manipulate the receipt for this system, 
   * false otherwise. 
   * */
  public boolean canManipulateReceipt() {
    return true;
  }
  
  /** 
   * @return true if an adversary can manipulate the vote for this system, 
   * false otherwise. 
   */
  public boolean canManipulateVote() {
    return true;
  }

  
  /**
   * @return true if an adversary can see the vote for this system, 
   * false otherwise.
   */
  public boolean canSeeVote() {
    return true;
  }
}
