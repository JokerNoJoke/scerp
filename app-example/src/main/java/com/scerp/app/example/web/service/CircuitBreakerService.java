package com.scerp.app.example.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scerp.app.example.base.web.exception.AppException;
import org.springframework.stereotype.Service;

/**
 * Circuit Breaker Service
 */
@Service
public class CircuitBreakerService {

    @HystrixCommand(fallbackMethod = "circuitBreaker", ignoreExceptions = AppException.class, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "666")
    })
    public String execute() {
        try {
            Thread.sleep(666);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    private String circuitBreaker(Throwable e) {
        return "fail: " + e.getMessage();
    }

}
