package com.cats.serviceLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class LogService implements Lifecycle {
    private static Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);

    @Autowired
    LogSenderRunnable logSenderRunnable;

    @PostConstruct //ta adnotacja powoduje, że metoda wykona się po uruchomieniu aplikacji
    public void setup() {
        LOGGER.info("PostConstruct setup");
        threadPool.scheduleAtFixedRate(logSenderRunnable, 60, 60, TimeUnit.SECONDS);
    }

    @Override
    public void start() {
        LOGGER.info("start");
    }

    @Override
    public void stop() {
        LOGGER.info("stop");

        threadPool.shutdown();

        while (isRunning()) {
            LOGGER.info("isRunning");

            try {
                LOGGER.info("Thread.sleep(300)");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                //ignoruj wyjątek
                LOGGER.info("InterruptedException");
            }
        }
    }

    @Override
    public boolean isRunning() {
        return threadPool.isTerminated();
    }
}