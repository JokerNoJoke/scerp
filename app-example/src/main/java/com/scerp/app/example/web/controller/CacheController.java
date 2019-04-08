package com.scerp.app.example.web.controller;

import com.scerp.app.example.web.service.SimpleCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private SimpleCacheService cacheService;

    @GetMapping
    public ResponseEntity<String> cache() {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
                //.eTag(version) // lastModified is also available
                .body("Cache");
    }

    @GetMapping("/set")
    public String setCache(@RequestParam("key") String key, @RequestParam String value) {
        cacheService.put(key, value);
        return "success: " + key;
    }

    @GetMapping("/get")
    public String getCache(@RequestParam("key") String key) {
        return cacheService.get(key);
    }

}
