package com.scerp.app.example.view.service.fallback;

import com.scerp.app.example.model.entity.IPInfo;
import com.scerp.app.example.view.service.EurekaOuterService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EurekaOuterServiceFallback implements FallbackFactory<EurekaOuterService> {

    @Override
    public EurekaOuterService create(Throwable throwable) {
        return () -> new IPInfo().setMessage(throwable.getMessage());
    }

}
