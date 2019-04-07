package com.scerp.app.example.web.controller;

import com.scerp.app.example.model.entity.IPInfo;
import com.scerp.app.example.web.service.CircuitBreakerService;
import com.scerp.app.example.web.client.EurekaOuterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private CircuitBreakerService innerService;
    @Autowired
    private EurekaOuterService outerService;

    @GetMapping("/")
    public IPInfo index() {
        return outerService.get();
    }

    @GetMapping("/remote")
    public String remote() {
        return innerService.execute();
    }

    @GetMapping("/model")
    public String exception(@ModelAttribute("default") Model model) {
        System.out.println(0 / 0);
        return model.toString();
    }

}
