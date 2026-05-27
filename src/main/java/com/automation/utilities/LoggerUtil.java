package com.automation.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger Utility Class for logging messages
 */
public class LoggerUtil {
    private static Logger logger;

    static {
        logger = LogManager.getLogger(LoggerUtil.class);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Exception e) {
        logger.error(message, e);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void fatal(String message, Exception e) {
        logger.fatal(message, e);
    }
}
