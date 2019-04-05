package com.scerp.app.example.view.service;

import com.scerp.app.example.config.FeignClientConfig;
import com.scerp.app.example.model.entity.IPInfo;
import com.scerp.app.example.view.service.fallback.EurekaOuterServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Eureka outer service
 */
@FeignClient(name = "AnyThing", url = "http://ip-api.com/json?lang=zh-CN", configuration = FeignClientConfig.class, fallbackFactory = EurekaOuterServiceFallback.class)
public interface EurekaOuterService {

    @GetMapping
    IPInfo get();

}
