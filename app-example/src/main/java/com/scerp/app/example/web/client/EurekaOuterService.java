package com.scerp.app.example.web.client;

import com.scerp.app.example.config.web.FeignClientConfig;
import com.scerp.app.example.model.entity.IPInfo;
import com.scerp.app.example.web.client.fallback.EurekaOuterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Eureka outer client
 */
@FeignClient(name = "AnyThing", url = "http://ip-api.com/json?lang=zh-CN", configuration = FeignClientConfig.class, fallbackFactory = EurekaOuterServiceFallback.class)
public interface EurekaOuterService {

    @GetMapping
    IPInfo execute();

}
