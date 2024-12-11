import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {
    // Define the Logger
    private static final Logger logger = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        // Test logging at various levels
        //logger.trace("This is a TRACE level log.");
        //logger.debug("This is a DEBUG level log.");
        logger.info("This is an INFO level log.");
        logger.warn("This is a WARN level log.");
        logger.error("This is an ERROR level log.");
        //logger.fatal("This is a FATAL level log.");
    }
}
