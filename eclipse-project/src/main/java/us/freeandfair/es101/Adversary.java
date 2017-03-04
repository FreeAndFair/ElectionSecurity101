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
 * A person or entity that wishes to manipulate the outcome of an election.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Adversary extends UserInterface {
  /** The election for this adversary. */
  protected final Election my_election;
  
  /**
   * Constructs a new Adversary from the specified Election.
   * 
   * @param the_election The election.
   */
  public Adversary(final Election the_election) {
    my_election = the_election;
  }
  
  /** 
   * @return The schema for the adversary interface. 
   */
  @Pure public String schema() {
    return "/adversary";
  }
  
  /**
   * Handle an HTTP request.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The data to return in response.
   */
  @Pure public String action(final Request the_request, final Response the_response) {
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", false);
    page_template.add("enable_refresh", true);
    page_template.add("refresh", "15");
    final ST adversary_template = StringTemplateUtil.loadTemplate("adversary");
    adversary_template.add("election", my_election);
    page_template.add("body", adversary_template.render());
    return page_template.render();

  }
}
