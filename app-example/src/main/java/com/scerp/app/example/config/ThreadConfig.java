package com.scerp.app.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ThreadConfig {

    private final int availableProcessors = Runtime.getRuntime().availableProcessors();
    private final int keepAliveSeconds = 66;
    private final int queueCapacity = 1024;
    private ThreadPoolExecutor.CallerRunsPolicy rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();

    @Bean("webAsyncThreadPool")
    public ThreadPoolTaskExecutor webAsyncThreadPool() {
        ThreadPoolTaskExecutor webAsyncThreadPool = new ThreadPoolTaskExecutor();
        webAsyncThreadPool.setThreadNamePrefix("TP-ASYNC-WEB-");
        webAsyncThreadPool.setCorePoolSize(availableProcessors / 2);
        webAsyncThreadPool.setAllowCoreThreadTimeOut(true);
        webAsyncThreadPool.setMaxPoolSize(availableProcessors * 9);
        webAsyncThreadPool.setKeepAliveSeconds(keepAliveSeconds);
        webAsyncThreadPool.setQueueCapacity(queueCapacity);
        webAsyncThreadPool.setRejectedExecutionHandler(rejectedExecutionHandler);
        webAsyncThreadPool.setWaitForTasksToCompleteOnShutdown(true);
        webAsyncThreadPool.initialize();
        return webAsyncThreadPool;
    }

    @Bean("asyncThreadPool")
    public ThreadPoolExecutor asyncThreadPool() {
        return new ThreadPoolExecutor(
                1, availableProcessors + 1,
                keepAliveSeconds, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(availableProcessors),
                new DefaultThreadFactory("TP-ASYNC-"),
                rejectedExecutionHandler
        );
    }

    @Bean("schedulerThreadPool")
    public ThreadPoolTaskScheduler schedulerThreadPool() {
        ThreadPoolTaskScheduler taskThreadPool = new ThreadPoolTaskScheduler();
        taskThreadPool.setThreadNamePrefix("TP-SCHEDULER-");
        taskThreadPool.setPoolSize(availableProcessors + 1);
        taskThreadPool.setRejectedExecutionHandler(rejectedExecutionHandler);
        taskThreadPool.setWaitForTasksToCompleteOnShutdown(true);
        taskThreadPool.initialize();
        return taskThreadPool;
    }

    /**
     * The default thread factory
     */
    private class DefaultThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}
