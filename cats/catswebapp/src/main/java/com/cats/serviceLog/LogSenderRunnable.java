package com.cats.serviceLog;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LogSenderRunnable implements Runnable {
    private static Logger LOGGER = LoggerFactory.getLogger(LogSenderRunnable.class);

    public void run() {
        LOGGER.info("App run :-)");
    }
}