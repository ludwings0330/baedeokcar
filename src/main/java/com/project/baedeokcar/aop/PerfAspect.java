package com.project.baedeokcar.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class PerfAspect {

    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        log.info("=== method [" + signature.getMethod() + "] start ===");

        // Method args
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();
        Object[] args = pjp.getArgs();

        for (int i = 0; i < parameterNames.length; i++) {
            log.info("[parameterName : " + parameterNames[i] + "] [parameterType : " + parameterNames[i] + "] [parameterValue : " + args[i].toString()+"]");
        }

        Object ret = pjp.proceed();


        long perfTime = System.currentTimeMillis() - begin;
        log.info("Performance Time : " + perfTime + "ms");

        return ret;
    }
}
