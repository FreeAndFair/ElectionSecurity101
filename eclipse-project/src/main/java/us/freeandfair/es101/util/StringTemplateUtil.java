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
  private static final String TEMPLATE_PATH = "us/freeandfair/es101/templates/";
  
  /**
   * The cache of already-loaded templates.
   */
  private static final Map<String, STGroup> LOADED = new HashMap<String, STGroup>();
  
  /**
   * Private constructor to prevent instantiation of this class.
   */
  private StringTemplateUtil() {
    // do nothing
  }
  
  /**
   * Gets one of our StringTemplate groups.
   * 
   * @param the_name The name of the group (e.g., "shared").
   * @return The group.
   */
  public static synchronized STGroup loadGroup(final String the_name) {
    STGroup result = LOADED.get(the_name);
    if (result == null) {
      try {
        result = new STGroupFile(TEMPLATE_PATH + the_name + ".stg", '$', '$');
        LOADED.put(the_name, result);
      } catch (final STException e) {
        throw new RuntimeException("Unable to load template group " + the_name, e);
      }
    } 
    return result;
  }
  
  /**
   * Gets one of our StringTemplates.
   * 
   * @param the_name The name of the template (e.g., "page").
   * @return The template.
   */
  public static synchronized ST loadTemplate(final String the_name) {
    STGroup group = LOADED.get(TEMPLATE_PATH);
    if (group == null) {
      try {
        group = new STGroupDir(TEMPLATE_PATH, '$', '$');
        LOADED.put(TEMPLATE_PATH, group);
      } catch (final STException e) {
        throw new RuntimeException("Unable to load default template group.");
      }
    }
    return group.getInstanceOf(the_name);
  }
}