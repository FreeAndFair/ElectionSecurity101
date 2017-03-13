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
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The timeout after which a vote is returned to the queue.
   */
  private static final int TIMEOUT = 180 * 1000; // 3 minutes
  
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
   * Handle votes that have timed out.
   */
  private synchronized void handleTimeouts() {
    final Set<Long> removed_ids = new HashSet<Long>();
    for (long id : my_in_progress.keySet()) {
      if (System.currentTimeMillis() - my_in_progress.get(id).getTimestamp() > TIMEOUT) {
        // return that vote to the queue
        my_queue.offer(my_in_progress.get(id));
        removed_ids.add(id);
      }
    }
    for (long id : removed_ids) {
      my_in_progress.remove(id);
      Main.LOGGER.info("returning ballot #" + id + " to queue after timeout.");
    }
  }
  
  /**
   * Handle an HTTP request for the initial manipulator setup.
   * 
   * @param the_request The HTTP request.
   * @param the_response The HTTP response.
   * @return The data to return in response.
   */
  public synchronized String handleInitialAction(final Request the_request,
                                                 final Response the_response) {
    handleTimeouts();
    final VoterAction va = my_queue.poll();
    final ST page_template = StringTemplateUtil.loadTemplate("page");
    page_template.add("enable_refresh", true);
    String refresh_string = "10; /";
    if (va != null) {
      refresh_string = "120;/manipulation?id=" + va.my_id + "&timeout";
      va.updateTimestamp();
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
  public synchronized String handleTimeout(final Request the_request,
                                           final Response the_response) {
    if (the_request.queryParams().contains("id")) {
      try {
        final long id = Long.parseLong(the_request.queryParams("id"));
        if (my_in_progress.containsKey(id)) {
          // re-queue the vote whose manipulation timed out
          my_queue.offer(my_in_progress.get(id));
          my_in_progress.remove(id);
          Main.LOGGER.info("returning ballot #" + id + " to queue after explicit timeout.");
        } else {
          Main.LOGGER.info("attempt to time out an unmanipulated ballot, #" + id);
        }
      } catch (final NumberFormatException e) {
        Main.LOGGER.info("attempt to time out an invalid ballot, #" + 
                         the_request.queryParams("id"));
      }
    }
    handleTimeouts();
    final ST page_template = StringTemplateUtil.loadTemplate("page");
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
  public synchronized String handleManipulate(final Request the_request,
                                              final Response the_response) {
    boolean vote_change = false;
    boolean receipt_change = false;
    boolean error = false;
    VoterAction va = null;
    
    if (the_request.queryParams().contains("id")) {
      final long id;
      try {
        id = Long.parseLong(the_request.queryParams("id"));
        if (my_in_progress.containsKey(id)) {
          va = my_in_progress.get(id);
          // check for a vote change
          if (the_request.queryParams().contains("vote")) {
            final String new_vote = the_request.queryParams("vote");
            if (my_election.getCandidates().contains(new_vote) && 
                !new_vote.equals(va.my_vote)) {
              va.my_manipulated_vote = new_vote;
              vote_change = true;
              Main.LOGGER.info("changed ballot #" + id + " vote from " + 
                               va.my_vote + " to " + new_vote);
            } else if (!new_vote.equals(va.my_vote)) {
              Main.LOGGER.info("attempt to change vote (" + va.my_vote + 
                               ") to invalid value " + new_vote);
              error = true;
            }
          }
          
          // check for a receipt change
          if (the_request.queryParams().contains("receipt")) {
            final String new_receipt = the_request.queryParams("receipt");
            if (my_election.getCandidates().contains(new_receipt) && 
                !new_receipt.equals(va.my_vote)) {
              va.my_manipulated_receipt = new_receipt;
              receipt_change = true;
              Main.LOGGER.info("changed ballot #" + id + " receipt from " +
                               va.my_vote + " to " + new_receipt);
            } else if (!new_receipt.equals(va.my_vote)) {
              Main.LOGGER.info("attempt to change receipt (" + va.my_vote +
                               ") to invalid value " + new_receipt);
              error = true;
            }
          }
          
          // cast the vote
          my_election.recordVoterAction(va);
          my_in_progress.remove(id);
        } else {
          Main.LOGGER.info("attempt to manipulate an invalid ballot, id " + id);
          error = true;
        }
      } catch (final NumberFormatException e) {
        Main.LOGGER.info("attempt to manipulate an invalid ballot, id " + 
                         the_request.queryParams("id"));
        error = true;
      }
    }
    handleTimeouts();
    ST result;
    if (error) {
      result = StringTemplateUtil.loadTemplate("page");
      result.add("enable_refresh", true);
      result.add("refresh", "30; /");
      final ST error_template = StringTemplateUtil.loadTemplate("error_occurred");
      result.add("body", error_template.render());
    } else {
      result = StringTemplateUtil.loadTemplate("page");
      result.add("enable_refresh", true);
      final String refresh_string = "120; /adversary";
      result.add("refresh", refresh_string);
      final ST manipulation_success_template = 
          StringTemplateUtil.loadTemplate("manipulation_success");
      manipulation_success_template.add("election", my_election);
      if (va != null) {
        manipulation_success_template.add("voting_system", va.getVotingSystem());
      }
      manipulation_success_template.add("vote_change", vote_change);
      manipulation_success_template.add("receipt_change", receipt_change);
      result.add("body", manipulation_success_template.render());
    }
    return result.render();
  }
}
