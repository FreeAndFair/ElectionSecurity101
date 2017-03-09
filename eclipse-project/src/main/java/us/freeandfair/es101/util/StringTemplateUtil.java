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

package us.freeandfair.es101.util;

import java.util.HashMap;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.compiler.STException;

import us.freeandfair.es101.Main;

/**
 * Handles loading of StringTemplate groups.
 * 
 * @author Daniel M. Zimmerman <dmz@freeandfair.us>
 * @version 1.0
 */
public final class StringTemplateUtil {
  /**
   * The path to all templates.
   */
  // no need for cross-platform file separators because ST handles them properly
  private static final String DEFAULT_TEMPLATE_GROUP = "us/freeandfair/es101/templates/all_templates.stg";

  /**
   * The cache of already-loaded template groups.
   */
  private static final Map<String, STGroup> LOADED = new HashMap<String, STGroup>();

  /**
   * Are we debugging templates?
   */
  private static boolean DEBUG_TEMPLATES = true;
  
  /**
   * The actual template group in use.
   */
  private static String template_group = DEFAULT_TEMPLATE_GROUP;
  
  /**
   * Private constructor to prevent instantiation of this class.
   */
  private StringTemplateUtil() {
    // do nothing
  }

  /**
   * Gets one of our StringTemplates.
   * 
   * @param the_name The name of the template (e.g., "page").
   * @return The template.
   */
  public static synchronized ST loadTemplate(final String the_name) {
    STGroup group = LOADED.get(template_group);
    if (group == null) {
      try {
        if (template_group.endsWith(".stg")) {
          group = new STGroupFile(template_group, '$', '$');
        } else {
          // assume directory
          group = new STGroupDir(template_group, '$', '$');
        }
        if (group.getInstanceOf("landing") == null) {
          Main.LOGGER.error("can't load templates from group " + template_group + "!");
        }
        LOADED.put(template_group, group);
      } catch (final STException e) {
        throw new RuntimeException("Unable to load default template group.");
      }
    } else if (DEBUG_TEMPLATES) {
      STGroup.trackCreationEvents = true;
    }

    return group.getInstanceOf(the_name);
  }
}
