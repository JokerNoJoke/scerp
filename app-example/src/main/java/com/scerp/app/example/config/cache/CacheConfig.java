package com.scerp.app.example.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CacheErrorHandlerConfig cacheErrorHandlerConfig;

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                Object key = super.generate(target, method, params);
                logger.info("Cache key Generator: " + key);
                return key;
            }
        };
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return cacheErrorHandlerConfig;
    }

}
