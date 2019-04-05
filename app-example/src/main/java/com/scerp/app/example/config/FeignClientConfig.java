package com.scerp.app.example.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RetryRule;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FeignClientConfig {

/*  @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "security");
    }*/

    @Bean
    public IPing ribbonPing() {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule() {
        return new RetryRule();
    }

    /**
     * @return {
     * NONE, No logging (DEFAULT).
     * BASIC, Log only the request method and URL and the response status code and execution time.
     * HEADERS, Log the basic information along with request and response headers.
     * FULL, Log the headers, body, and metadata for both requests and responses.
     * }
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
