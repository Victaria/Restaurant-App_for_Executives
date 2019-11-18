package sample.Loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogMain {
    static Logger log = LogManager.getLogger();

    public static void printLog(){
        log.info("First error");
        log.log(Level.INFO, "Warning!!");
        try {
            throw new RuntimeException("Exeption 1");
        } catch (RuntimeException e){
            log.log(Level.ERROR, "runtime", e);
        }
    }
}
