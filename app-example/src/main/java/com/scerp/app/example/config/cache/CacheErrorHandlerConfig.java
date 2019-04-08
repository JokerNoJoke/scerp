package com.scerp.app.example.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

@Component
public class CacheErrorHandlerConfig implements CacheErrorHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void log(RuntimeException exception, Cache cache, Object key, Object value) {
        logger.info(
                new StringBuilder("CacheErrorHandler: ")
                        .append("cache").append("=").append(cache.getName()).append(" ")
                        .append("key").append("=").append(key).append(" ")
                        .append("value").append("=").append(value).append(" ")
                        .append("exception").append("=").append(exception.getMessage())
                        .toString()
        );
    }

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log(exception, cache, key, null);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log(exception, cache, key, value);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log(exception, cache, key, null);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log(exception, cache, null, null);
    }

}
