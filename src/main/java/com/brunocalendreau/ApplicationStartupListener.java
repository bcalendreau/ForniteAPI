package com.brunocalendreau;

import com.brunocalendreau.server.event.TokenUpdatedEvent;
import com.brunocalendreau.server.fortnite.FortniteLoginTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class ApplicationStartupListener{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);
    private final FortniteLoginTask fortniteLoginTask;
    private ScheduledExecutorService executorService;

    @Autowired
    public ApplicationStartupListener(FortniteLoginTask fortniteLoginTask) {
        this.fortniteLoginTask = fortniteLoginTask;
    }

    @EventListener
    public void onStartUpEvent(ContextRefreshedEvent event) {
        logger.info("Application started, setting up task");
        if (executorService == null) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        try {
            executorService.scheduleAtFixedRate(fortniteLoginTask, 0, 7, TimeUnit.HOURS);
        } catch (Throwable t) {
            logger.error("Error with login thread", t);
        }
    }
}
