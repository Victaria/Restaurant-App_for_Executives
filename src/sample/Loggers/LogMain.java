package sample.Loggers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogMain {
    static Logger log = LogManager.getLogger();

    public static void printErrorLog(String errName, Exception e){
        log.log(Level.ERROR, errName, e);
    }

    public static void printInfoLog(String infoName){
        log.log(Level.INFO, infoName);
    }
}
