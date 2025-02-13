package com.example.courses.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AroundAspect {

    @Pointcut("@annotation(AroundAnnotation)")
    public void aroundCall(){};

    @Around("aroundCall()")
    public Object call(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Вызов around {}", proceedingJoinPoint.toLongString());
        return proceedingJoinPoint.proceed();
    }
}
