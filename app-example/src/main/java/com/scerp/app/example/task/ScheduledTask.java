package com.scerp.app.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * For more fine-grained control, you can use SchedulingConfigurer
 */
@Component
public class ScheduledTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AsyncTask task;

    @Scheduled(initialDelay = 6L, fixedRate = 666666L)
    public void scheduled() {
        logger.info(
                new StringBuffer(this.getClass().getName())
                        .append(Thread.currentThread().getName())
                        .append(":")
                        .append(LocalDateTime.now().toString())
                        .toString()
        );
        task.async();
    }

}
