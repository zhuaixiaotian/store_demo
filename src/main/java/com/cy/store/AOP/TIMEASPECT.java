package com.cy.store.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
@Aspect
public class TIMEASPECT {

    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object  around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
        return  proceed;

    }
}
