package com.bfs.onboard.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {

    @Pointcut("execution(* com.bfs.onboard.dao.*.*(..))")
    public void inDaoLayer1() {}

    @Pointcut("within(com.bfs.onboard.dao..*)")
    public void inDaoLayer2() {}

    @Pointcut("bean(*Dao)")
    public void inDaoLayer3() {}

    @Pointcut(
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void logRequest() {}

    @Pointcut("within(com.bfs.onboard.controller.*)")
    public void inWebLayer() {}

}
