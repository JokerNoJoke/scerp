package com.scerp.app.example.web.client;

import com.scerp.app.example.base.web.WebClientConfig;
import com.scerp.app.example.web.client.fallback.EurekaInnerServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Eureka inner client
 */
@FeignClient(name = "APP-EXAMPLE", path = "/prefix", configuration = WebClientConfig.class, fallbackFactory = EurekaInnerServiceFallback.class)
public interface EurekaInnerService {

    @GetMapping("/404")
    String execute();

}
