package com.scerp.app.example.config.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SchedulingTaskConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler schedulingTaskThreadPool = new ThreadPoolTaskScheduler();
        schedulingTaskThreadPool.setThreadNamePrefix("TP-TASK-SCHEDULING-");
        schedulingTaskThreadPool.setPoolSize(6);
        schedulingTaskThreadPool.setWaitForTasksToCompleteOnShutdown(true);
        schedulingTaskThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        schedulingTaskThreadPool.initialize();

        scheduledTaskRegistrar.setTaskScheduler(schedulingTaskThreadPool);
    }

}
