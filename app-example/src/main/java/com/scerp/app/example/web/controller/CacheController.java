package com.scerp.app.example.web.controller;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

    @GetMapping("/cache")
    public ResponseEntity<String> cache() {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
                //.eTag(version) // lastModified is also available
                .body("Cache");
    }

}
