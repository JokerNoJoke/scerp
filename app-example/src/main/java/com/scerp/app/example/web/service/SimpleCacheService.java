package com.scerp.app.example.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple Cache Service
 */
@Service
@CacheConfig(cacheNames = "example")
public class SimpleCacheService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, String> example = new HashMap<>(6);

    {
        example.put("joker", "123456");
    }

    @CachePut
    public String put(String key, String value) {
        return example.put(key, value);
    }

    @Cacheable
    public String get(String key) {
        logger.info("Now no cache");
        return example.get(key);
    }

}
