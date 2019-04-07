package com.scerp.app.example.web.client.fallback;

import com.scerp.app.example.web.client.EurekaInnerService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EurekaInnerServiceFallback implements FallbackFactory<EurekaInnerService> {

    @Override
    public EurekaInnerService create(Throwable throwable) {
        return () -> throwable.getMessage();
    }

}
