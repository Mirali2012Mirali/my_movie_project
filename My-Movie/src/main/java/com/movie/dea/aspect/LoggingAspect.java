package com.movie.dea.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.movie.dea.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("AOP LOG -> method called: " + joinPoint.getSignature().getName());
    }
}
