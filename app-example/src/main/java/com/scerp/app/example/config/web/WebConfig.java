package com.scerp.app.example.config.web;

import com.scerp.app.example.config.web.interceptor.WebAsyncHandlerInterceptor;
import com.scerp.app.example.config.web.interceptor.WebCallableProcessingInterceptor;
import com.scerp.app.example.config.web.interceptor.WebDeferredResultProcessingInterceptor;
import com.scerp.app.example.config.web.interceptor.WebHandlerInterceptor;
import com.scerp.app.example.config.web.share.WebShareParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private WebHandlerInterceptor webHandlerInterceptor;
    @Autowired
    private WebAsyncHandlerInterceptor webAsyncHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webHandlerInterceptor);
        registry.addInterceptor(webAsyncHandlerInterceptor);
    }

    @Autowired
    private WebCallableProcessingInterceptor webCallableProcessingInterceptor;
    @Autowired
    private WebDeferredResultProcessingInterceptor webDeferredResultProcessingInterceptor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        ThreadPoolTaskExecutor webThreadPool = new ThreadPoolTaskExecutor();
        webThreadPool.setThreadNamePrefix("TP-WEB-");
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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/public", "classpath:/static/")
                .setCachePeriod(31556926);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @Autowired
    private WebShareParameter webShareParameter;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(webShareParameter);
    }

}
