package com.scerp.app.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DefaultControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.scerp.app.example.web.controller.*.*(..))")
    private void anyOldTransfer() {
    }

    @Before("anyOldTransfer()")
    public void before(JoinPoint jp) {
    }

    @AfterReturning(pointcut = "anyOldTransfer()", returning = "result")
    public void afterReturning(Object result) {
    }

    @AfterThrowing(pointcut = "anyOldTransfer()", throwing = "e")
    public void afterThrowing(Exception e) {
    }

/*  Aspect try  Exception it can shield ExceptionHandler

    @Around("anyOldTransfer()")
    public Object execute(ProceedingJoinPoint pjp) {
        Object result = null;
        StringBuilder info = new StringBuilder();
        long startTime = System.currentTimeMillis();

        // Before
        info.append("before[")
                .append(pjp.getTarget().getClass().getName()).append("->")
                .append(pjp.getSignature().getName())
                .append(pjp.getArgs()).append("]");
        logger.info(info.toString());

        // Proceed
        try {
            result = pjp.proceed();
            info.append("proceed[").append(result);
        } catch (Throwable e) {
            // Exception
            info.append("exception[").append(e.getMessage());
        }
        info.append("]");

        // After
        info.append("executeTime: ").append(System.currentTimeMillis() - startTime);

        return result;
    }*/

}
