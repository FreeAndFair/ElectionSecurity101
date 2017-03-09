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
  public String getExplanationText() {
    return "";
  }

  /**
   * @return text that is presented after casting a ballot.
   */
  @Pure
  public String getAfterVotingText() {
    return "";
  }
  
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#action(spark.Request, spark.Response)
   */
  @Pure @Override
  public String action(final Request the_request, final Response the_response) {
    final boolean vote = the_request.queryParams().contains("vote");

    String result = "";
    
    if (vote) {
      result = iVotedPageSetup(the_request.queryParams("vote")).render();
    } else {
      result = votingSystemPageSetup().render();
    }
    
    return result;
  }  

  /**
   * Create the general "I Voted" page that voting system subclasses will customize,
   * and casts the appropriate vote.
   * 
   * @param the_vote The vote cast by the voter, if any.
   * @return the template on which to build.
   */
  protected ST iVotedPageSetup(final String the_vote) {
    ST page_template;
    page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "60; /");
    if (the_vote != null && my_election.getCandidates().contains(the_vote)) {
      final VoterAction va = new VoterAction(this, the_vote);
      my_queue.offer(va);
      Main.LOGGER.info("Cast Ballot: ID = " + va.getID() + ", Voter Intent = '" + the_vote + "'");
      final ST i_voted_template = StringTemplateUtil.loadTemplate("i_voted");
      i_voted_template.add("election", my_election);
      i_voted_template.add("voter_action", va);
      i_voted_template.add("receipt", getReceipt(va));
      page_template.add("body", i_voted_template.render());
    } else {  
      // invalid vote
      final ST error_occurred = StringTemplateUtil.loadTemplate("error_occurred");
      page_template.add("body", error_occurred.render());
    }
    return page_template;
  }
  
  /**
   * Create the general template voting system page from which all voting system
   * subclasses will customize.
   * @return the string template on which to build.
   */
  @Pure
  protected ST votingSystemPageSetup() {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "120; /");
    final ST voting_system_template = StringTemplateUtil.loadTemplate("voting_system");
    voting_system_template.add("election", my_election);
    voting_system_template.add("explanation", getExplanationText());
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
  
  /**
   * @return true if a receipt is generated in this voting system, false otherwise.
   */
  public boolean isReceiptGenerated() {
    return false;
  }
  
  /**
   * @param the_voter_action the VoterAction for which we need a receipt.
   * @return receipt data for the specified VoterAction in this voting system.
   */
  public String getReceipt(final VoterAction the_voter_action) {
    return "Ballot #" + the_voter_action.getID();
  }
}
