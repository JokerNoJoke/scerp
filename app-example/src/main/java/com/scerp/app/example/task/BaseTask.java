package com.scerp.app.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * For more fine-grained control, you can use AsyncConfigurer
 */
@Component
public class BaseTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public void async() {
        try {
            Thread.sleep(666);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(
                new StringBuffer(this.getClass().getName())
                        .append(Thread.currentThread().getName())
                        .append(":")
                        .append(LocalDateTime.now().toString())
                        .toString()
        );
    }

}
