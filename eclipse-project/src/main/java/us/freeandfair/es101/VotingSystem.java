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

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jmlspecs.annotation.Nullable;
import org.jmlspecs.annotation.Pure;
import org.stringtemplate.v4.ST;

import spark.Request;
import spark.Response;
import us.freeandfair.es101.util.StringTemplateUtil;

/**
 * The operational means by which a voter captures their contest choices.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VotingSystem extends UserInterface {
  /** The voter action queue on which this voting system can enqueue votes. */
  protected Queue<VoterAction> my_queue;
  
  /**
   * Create a new voting system.
   */
  @Pure
  public VotingSystem() {
    super(null);
    my_queue = new ConcurrentLinkedQueue<VoterAction>();
  }
  
  @Pure
  public String getName() {
    return "";
  }
  
  @Pure
  public String getUsageRegions() {
    return "";
  }
  
  /**
   * Assign this voting system to that election.
   * @param the_election to which to assign this voting system.
   */
  public void setElection(final Election the_election) {
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
    page_template.add("enable_results", false);
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "15");
    final ST voting_system_template = StringTemplateUtil.loadTemplate("voting_system");
    voting_system_template.add("election", my_election);
    voting_system_template.add("explanation", explanationText());
    voting_system_template.add("schema", getSchema());
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
