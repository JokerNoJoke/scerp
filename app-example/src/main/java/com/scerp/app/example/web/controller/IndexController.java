package com.scerp.app.example.web.controller;

import com.scerp.app.example.model.entity.IPInfo;
import com.scerp.app.example.web.client.EurekaOuterService;
import com.scerp.app.example.web.service.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private CircuitBreakerService innerService;
    @Autowired
    private EurekaOuterService outerService;

    @GetMapping("/")
    public String index(String shareParameter) {
        return shareParameter;
    }

    @GetMapping("/inner")
    public String inner() {
        return innerService.execute();
    }

    @GetMapping("/outer")
    public IPInfo outer() {
        return outerService.execute();
    }

}
