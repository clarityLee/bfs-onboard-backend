package com.bfs.onboard.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExecutionTimeAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("com.bfs.onboard.aop.PointCuts.requests()")
    public Object executionTimeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String signature = pjp.getSignature().toString();
        Object clazz = pjp.getSignature().getDeclaringType();

        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        log.info("class: "+clazz);
        log.info(signature+ " execution time: "+elapsedTime+" ms");
        log.info("return value: "+result.toString());
        return result;
    }
}
