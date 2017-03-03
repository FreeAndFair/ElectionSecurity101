
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * A small test class for Spark.
 * 
 * @author Daniel M. Zimmerman
 * @version 0
 */
public final class Test {
  /**
   * The port number.
   */
  public static final int PORT = 8888;
  
  /**
   * Hidden constructor to prevent instantiation.
   */
  private Test() {
  }
  
  /**
   * The main method.
   * 
   * @param the_args Ignored.
   */
  public static void main(final String[] the_args) {
    // the separate election event logger is obtained in this way
    final Logger logger = LogManager.getLogger("election");
    port(PORT);
    get("/hello", (the_req, the_res) -> "Hello World");
    logger.info("hello world!");
  }
}
