package com.scerp.app.example.view.service.fallback;

import com.scerp.app.example.view.service.EurekaInnerService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EurekaInnerServiceFallback implements FallbackFactory<EurekaInnerService> {

    @Override
    public EurekaInnerService create(Throwable throwable) {
        return () -> throwable.getMessage();
    }

}
