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
  /**
   * Create a new voting system.
   */
  @Pure
  public VotingSystem() {
    super(null, null);
  }
  
  /**
   * @return the colloquial name of this voting system.
   */
  @Pure
  public String getName() {
    return "";
  }

  /**
   * @return the regions of the country in which this voting system is used today.
   */
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
   * Assign a queue of voter actions to this voting system.
   * @param the_queue is the queue of actions.
   */
  public void setQueue(final Queue<VoterAction> the_queue) {
    my_queue = the_queue;
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
    final boolean timeout = the_request.queryParams().contains("timeout");

    String result = "";
    
    if (timeout) {
      result = my_election.my_voting_system_choice.action(the_request, the_response);
    } else {
      result = votingSystemPageSetup().render();
    }
    
    return result;
  }  

  /**
   * Create the general template for thanking the voter for voting.  Its contents
   * are customized via queries on subclasses.
   * @return the string template on which to build.
   */
  @Pure
  protected ST votingSystemThankYouPageSetup() {
    return null;
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
    final String refresh_string = "120; /";
    page_template.add("refresh", refresh_string);
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
  public boolean isReceiptManipulable() {
    return true;
  }
  
  /** 
   * @return true if an adversary can manipulate the vote for this system, 
   * false otherwise. 
   */
  public boolean isVoteManipulable() {
    return true;
  }
  
  /**
   * @return true if an adversary can see the vote for this system, 
   * false otherwise.
   */
  public boolean isVoteVisible() {
    return true;
  }
  
  /**
   * @return the name of a "receipt" in this voting system.
   */
  public String getReceiptName() {
    return "receipt";
  }
}
