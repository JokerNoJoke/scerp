package com.scerp.app.example.config.view;

import com.scerp.app.example.config.view.interceptor.WebAsyncHandlerInterceptor;
import com.scerp.app.example.config.view.interceptor.WebCallableProcessingInterceptor;
import com.scerp.app.example.config.view.interceptor.WebDeferredResultProcessingInterceptor;
import com.scerp.app.example.config.view.interceptor.WebHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private WebHandlerInterceptor webHandlerInterceptor;
    @Autowired
    private WebAsyncHandlerInterceptor webAsyncHandlerInterceptor;
    @Autowired
    private WebCallableProcessingInterceptor webCallableProcessingInterceptor;
    @Autowired
    private WebDeferredResultProcessingInterceptor webDeferredResultProcessingInterceptor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        ThreadPoolTaskExecutor webThreadPool = new ThreadPoolTaskExecutor();
        webThreadPool.setThreadNamePrefix("web-thread-pool-");
        webThreadPool.setCorePoolSize(6);
        webThreadPool.setMaxPoolSize(666);
        webThreadPool.setKeepAliveSeconds(666);
        webThreadPool.setAllowCoreThreadTimeOut(false);
        webThreadPool.setQueueCapacity(1024);
        webThreadPool.setWaitForTasksToCompleteOnShutdown(true);
        webThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        webThreadPool.initialize();

        configurer.setTaskExecutor(webThreadPool)
                .setDefaultTimeout(6666)
                .registerCallableInterceptors(webCallableProcessingInterceptor)
                .registerDeferredResultInterceptors(webDeferredResultProcessingInterceptor);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webHandlerInterceptor);
        registry.addInterceptor(webAsyncHandlerInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

}
