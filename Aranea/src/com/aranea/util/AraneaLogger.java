package com.aranea.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AraneaLogger {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void log(String message) {
        log(message, Level.INFO);
    }

    public void log(String message, Level level) {
        LOGGER.log(level, message);
    }
}
