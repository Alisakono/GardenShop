package com.telran.gardenshop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = LogManager.getLogger(LoggerAspect.class);

    @Pointcut("within(com.telran.gardenshop.controller..*) || within(com.telran.gardenshop.service..*)")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.debug("Method {} from Class {} started with args:{}",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                 Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "returnValue")
    public void afterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
        logger.debug("Method {} from Class {} completed with return value: {}",
                joinPoint.getSignature().getName(),
                 joinPoint.getSourceLocation().getWithinType().getName(),
                 returnValue != null ? returnValue.toString() : null);
    }
}
