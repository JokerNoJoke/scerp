package com.scerp.app.example.config.web.handler;

import com.scerp.app.example.exception.AppException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Aspect try  Exception it can shield ExceptionHandler
 */
@RestControllerAdvice(annotations = RestController.class)
public class WebRestControllerAdvice {

    @ExceptionHandler(value = AppException.class)
    public Map exceptionHandler(AppException e) {
        return new HashMap(1) {{
            put("id", "AppExceptionHandler");
            put("msg", e.getMessage());
        }};
    }

    @ExceptionHandler(value = Exception.class)
    public Map exceptionHandler(Exception e) {
        return new HashMap(1) {{
            put("id", "ExceptionHandler");
            put("msg", e.getMessage());
        }};
    }

}
