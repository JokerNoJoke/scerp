package com.scerp.app.example.view.service;

import com.scerp.app.example.config.FeignClientConfig;
import com.scerp.app.example.view.service.fallback.EurekaInnerServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Eureka inner service
 */
@FeignClient(name = "APP-EXAMPLE", path = "/prefix", configuration = FeignClientConfig.class, fallbackFactory = EurekaInnerServiceFallback.class)
public interface EurekaInnerService {

    @GetMapping("/404")
    String execute();

}
