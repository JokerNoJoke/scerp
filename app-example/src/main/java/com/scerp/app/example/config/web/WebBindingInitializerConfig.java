package com.scerp.app.example.config.web;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;

import java.beans.PropertyEditorSupport;

/**
 * @InitBinder
 */
@Configuration
public class WebBindingInitializerConfig extends ConfigurableWebBindingInitializer {

    @Override
    public void initBinder(WebDataBinder binder) {
        // Config XSS
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                super.setAsText(Jsoup.clean(text, Whitelist.basic()));
            }
        });
        super.initBinder(binder);
    }

/*  @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                super.setAsText(Jsoup.clean(text, Whitelist.basic()));
            }
        });
    }*/

}
