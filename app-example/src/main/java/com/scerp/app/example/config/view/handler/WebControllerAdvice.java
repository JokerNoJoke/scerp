package com.scerp.app.example.config.view.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebControllerAdvice {

    /**
     * initialize the model prior to any @RequestMapping method invocation.
     */
    @ModelAttribute("default")
    public Model modelHandle(Model model, HttpServletRequest request) {
        return model.addAttribute("IP", request.getRemoteAddr());
    }

}
