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

    @Around("com.bfs.onboard.aop.PointCuts.inDaoLayer2()")
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

    @Before("com.bfs.onboard.aop.PointCuts.logRequest()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Incoming request: " + joinPoint.getSignature());
    }

    @AfterReturning(value = "com.bfs.onboard.aop.PointCuts.inWebLayer()", returning = "res")
    public void afterReturningAdvice(Object res){
        log.info("Web Layer After Returning Advice ");
        log.info("The return value is "+res.toString());
    }

}
