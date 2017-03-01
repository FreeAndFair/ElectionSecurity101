import static spark.Spark.get;
import static spark.Spark.port;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Test {
  public static void main(String[] args) {
    // the separate election event logger is obtained in this way
    Logger logger = LogManager.getLogger("election");
    port(8888);
    get("/hello", (req, res) -> "Hello World");
    logger.info("hello world!");
  }
}
