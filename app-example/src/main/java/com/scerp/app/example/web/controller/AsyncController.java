package com.scerp.app.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @GetMapping("/callable")
    public Callable<String> callable() {
        return () -> {
            Thread.sleep(1024);
            return "Callable";
        };
    }

    @GetMapping("/callableExtend")
    public WebAsyncTask<String> callableExtend() {
        return new WebAsyncTask<>(666, () -> {
            Thread.sleep(1024);
            return "WebAsyncTask";
        });
    }

    private DeferredResult<String> deferredResult = new DeferredResult<>(6666L);

    @GetMapping("/deferred/{any}")
    public String setDeferred(@PathVariable String any) {
        deferredResult.setResult(any);
        return "success: " + any;
    }

    @GetMapping("/deferred")
    public DeferredResult<String> getDeferred() {
        return deferredResult;
    }

    private ResponseBodyEmitter emitter = new ResponseBodyEmitter();

    @GetMapping("/events/{any}")
    public String setEvents(@PathVariable String any) {
        try {
            emitter.send(any);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success: " + any;
    }

    @GetMapping("/events/complete")
    public String completeEvents() {
        emitter.complete();
        return "complete";
    }

    @GetMapping("/events")
    public ResponseBodyEmitter getEvents() {
        return emitter;
    }

}
