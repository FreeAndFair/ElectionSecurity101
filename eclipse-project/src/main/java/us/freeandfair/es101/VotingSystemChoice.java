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
 * @description A UI that lets a voter choose which kind of voting system they wish
 * to use.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class VotingSystemChoice extends UserInterface {
  /**
   * Create a new UI for voting system choice for the election.
   * @param the_election the election in question.
   * @param the_queue The queue of voter actions.
   */
  @Pure
  public VotingSystemChoice(final Election the_election, final Queue<VoterAction> the_queue) {
    super(the_election, the_queue);
  }
  
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#my_url_schema()
   */
  @Pure @Override
  public String getSchema() {
    return "/voting_system_choice";
  }

  /**
    * Display the choices that the voter has to choose which a voting system to use.
    * @see us.freeandfair.es101.UserInterface#action()
    */
  @Override
  public String action(final Request the_request, final Response the_response) {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", true);
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "60; /?timeout");
    final ST voter_template =
        StringTemplateUtil.loadTemplate("voter");
    // voter_template.inspect();
    voter_template.add("election", my_election);
    page_template.add("body", voter_template.render());
    return page_template.render();
  }
}
