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
   */
  @Pure
  public VotingSystemChoice(final Election the_election) {
    super(the_election);
  }
  
  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#my_url_schema()
   */
  @Pure @Override
  public String schema() {
    return "/voting_system_choice";
  }

  /* (non-Javadoc)
   * @see us.freeandfair.es101.UserInterface#action()
   */
  /**
    * Display the choices that the voter has to choose which a voting system to use.
    */
  @Override
  public String action(final Request the_request, final Response the_response) {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", false);
    page_template.add("enable_refresh", false);
    page_template.add("refresh", "15");
    final ST voting_system_choice_template = 
        StringTemplateUtil.loadTemplate("voting_system_choice");
    voting_system_choice_template.add("voting_systems", my_election.my_voting_systems);
    page_template.add("body", voting_system_choice_template.render());
    return page_template.render();
  }
}
