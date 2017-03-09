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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
  private static final URL TEMPLATE_URL = ClassLoader.getSystemResource("us/freeandfair/es101/templates/");

  /**
   * The cache of already-loaded templates.
   */
  private static final Map<URL, STGroup> LOADED = new HashMap<URL, STGroup>();

  /**
   * Are we debugging templates?
   */
  private static boolean DEBUG_TEMPLATES = true;
  
  /**
   * Private constructor to prevent instantiation of this class.
   */
  private StringTemplateUtil() {
    // do nothing
  }

  /**
   * Append a path to a base URL.
   * 
   * @param the_base_url The base URL.
   * @param the_extra_path The path to append.
   * @return The resulting URL, or null if one couldn't be created.
   */
  public static URL concatenate(URL the_base_url, String the_extra_path) {
    try {
      final URI uri = the_base_url.toURI();
      final String new_path = uri.getPath() + '/' + the_extra_path;
      final URI new_uri = uri.resolve(new_path);
      return new_uri.toURL();
    } catch (final URISyntaxException | MalformedURLException e) {
      return null;
    }
  }
  
  /**
   * Gets one of our StringTemplate groups.
   * 
   * @param the_name The name of the group (e.g., "shared").
   * @return The group.
   */
  public static synchronized STGroup loadGroup(final String the_name) {
    final URL load_url = concatenate(TEMPLATE_URL, the_name);
    STGroup result = LOADED.get(load_url);
    if (result == null) {
      try {
        result = new STGroupFile(load_url, "UTF-8", '$', '$');
        LOADED.put(load_url, result);
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
    STGroup group = LOADED.get(TEMPLATE_URL);
    if (group == null) {
      try {
        group = new STGroupDir(TEMPLATE_URL, "UTF-8", '$', '$');
        if (group.getInstanceOf("adversary") == null) {
          Main.LOGGER.error("can't load templates!");
        }
        LOADED.put(TEMPLATE_URL, group);
      } catch (final STException e) {
        throw new RuntimeException("Unable to load default template group.");
      }
    } else if (DEBUG_TEMPLATES) {
      STGroup.trackCreationEvents = true;
    }

    return group.getInstanceOf(the_name);
  }
}
