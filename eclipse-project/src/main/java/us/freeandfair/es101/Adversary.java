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
 * A person or entity that wishes to manipulate the outcome of an election.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Adversary extends UserInterface {
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Constructs a new adversary for the election.
   * @param the_election the election in question.
   * @param the_queue The queue of voter actions.
   */
  @Pure
  public Adversary(final Election the_election, final Queue<VoterAction> the_queue) {
    super(the_election, the_queue);
  }
  
  @Pure @Override
  public String getSchema() {
    return "/adversary";
  }

  /**
   * Display the top-level UI of the adversary.
   * @see us.freeandfair.es101.UserInterface#action()
   */
  @Pure @Override
  public String action(final Request the_request, final Response the_response) {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "60; /");
    final ST adversary_template = StringTemplateUtil.loadTemplate("adversary");
    adversary_template.add("election", my_election);
    page_template.add("body", adversary_template.render());
    return page_template.render();
  }
}
