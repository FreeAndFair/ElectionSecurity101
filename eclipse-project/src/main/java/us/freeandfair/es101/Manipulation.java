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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.jmlspecs.annotation.Pure;
import org.stringtemplate.v4.ST;

import spark.Request;
import spark.Response;
import us.freeandfair.es101.util.StringTemplateUtil;

/**
 * An action of an adversary with the intention of manipulating the outcome of an election.
 *  
 * @version 1.0
 * @author Joseph Kiniry <kiniry@freeandfair.us>
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 */
public class Manipulation extends UserInterface {
  /**
   * The table of voter action manipulations that are in progress.
   */
  private final Map<Long, VoterAction> my_in_progress;
  
  /**
   * Constructs a new Manipulation from the specified Election.
   * 
   * @param the_election The election.
   * @param the_queue The queue of voter actions.
   */
  public Manipulation(final Election the_election, final Queue<VoterAction> the_queue) {
    super(the_election, the_queue);
    my_in_progress = Collections.synchronizedMap(new HashMap<Long, VoterAction>());
  }
  
  /** 
   * @return The schema for the adversary interface. 
   */
  @Pure public String getSchema() {
    return "/manipulation";
  }
  
  /**
   * Handle an HTTP request.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The data to return in response.
   */
  public String action(final Request the_request, final Response the_response) {
    final boolean timeout = the_request.queryParams().contains("timeout");
    final boolean manipulate = the_request.queryParams().contains("manipulate");
   
    String result = "";
    
    if (timeout) {
      result = handleTimeout(the_request, the_response);
    } else if (manipulate) {
      result = handleManipulate(the_request, the_response);
    } else {
      result = handleInitialAction(the_request, the_response);
    }
    
    return result;
  }
  
  /**
   * Handle an HTTP request for the initial manipulator setup.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The data to return in response.
   */
  public String handleInitialAction(final Request the_request, final Response the_response) {
    final VoterAction va = my_queue.poll();
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", false);
    page_template.add("enable_refresh", true);
    String refresh_string = "15; /adversary";
    if (va != null) {
      refresh_string = "120;/manipulation?id=" + va.my_id + "&timeout";
      my_in_progress.put(va.my_id, va);
    }
    page_template.add("refresh", refresh_string);
    final ST manipulation_template = StringTemplateUtil.loadTemplate("manipulation");
    manipulation_template.add("election", my_election);
    manipulation_template.add("voter_action", va);
    page_template.add("body", manipulation_template.render());
    return page_template.render();
  }
  
  /**
   * Handle an HTTP request for a manipulator timeout.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The data to return in response.
   */
  public String handleTimeout(final Request the_request, final Response the_response) {
    if (the_request.queryParams().contains("id")) {
      try {
        long id = Long.parseLong(the_request.queryParams("id"));
        if (my_in_progress.containsKey(id)) {
          // re-queue the vote whose manipulation timed out
          my_queue.offer(my_in_progress.get(id));
          my_in_progress.remove(id);
          Main.LOGGER.info("manipulation of ballot id " + id + " timed out, re-enqueuing");
        } else {
          Main.LOGGER.info("attempt to time out an unmanipulated ballot, id " + id);
        }
      } catch (final NumberFormatException e) {
        Main.LOGGER.info("attempt to time out an invalid ballot, id " + the_request.queryParams("id"));
      }
    }
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", false);
    page_template.add("enable_refresh", true);
    final String refresh_string = "0; /adversary";
    page_template.add("refresh", refresh_string);
    final ST manipulation_template = StringTemplateUtil.loadTemplate("manipulation");
    manipulation_template.add("election", my_election);
    page_template.add("body", "Manipulation Action Timed Out");
    return page_template.render();
  }

  /**
   * Handle an HTTP request for an actual manipulation.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The data to return in response.
   */
  public String handleManipulate(final Request the_request, final Response the_response) {
    boolean status = false;
    boolean vote_change = false;
    boolean receipt_change = false;
    
    if (the_request.queryParams().contains("id")) {
      long id;
      try {
        id = Long.parseLong(the_request.queryParams("id"));
        if (my_in_progress.containsKey(id)) {
          final VoterAction va = my_in_progress.get(id);
          // check for a vote change
          if (the_request.queryParams().contains("vote")) {
            String new_vote = the_request.queryParams("vote");
            if (my_election.getCandidates().contains(new_vote) && !new_vote.equals(va.my_vote)) {
              va.my_manipulated_vote = new_vote;
              vote_change = true;
            } else if (!new_vote.equals(va.my_vote)) {
              Main.LOGGER.info("attempt to change vote (" + va.my_vote + ") to invalid value " + new_vote);
            }
          }
          
          // check for a receipt change
          if (the_request.queryParams().contains("receipt")) {
            String new_receipt = the_request.queryParams("receipt");
            if (my_election.getCandidates().contains(new_receipt) && !new_receipt.equals(va.my_vote)) {
              va.my_manipulated_receipt = new_receipt;
              receipt_change = true;
            } else if (!new_receipt.equals(va.my_vote)) {
              Main.LOGGER.info("attempt to change receipt (" + va.my_vote + ") to invalid value " + new_receipt);
            }
          }
          
          // cast the vote
          my_election.recordVoterAction(va);
          my_in_progress.remove(id);
        } else {
          Main.LOGGER.info("attempt to manipulate an invalid ballot, id " + id);
        }
      } catch (final NumberFormatException e) {
        Main.LOGGER.info("attempt to manipulate an invalid ballot, id " + the_request.queryParams("id"));
      }
    }
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_results", false);
    page_template.add("enable_refresh", true);
    String refresh_string = "120; /adversary";
    page_template.add("refresh", refresh_string);
    final ST manipulation_success_template = StringTemplateUtil.loadTemplate("manipulation_success");
    manipulation_success_template.add("election", my_election);
    page_template.add("body", manipulation_success_template.render());
    return page_template.render();
  }
}
