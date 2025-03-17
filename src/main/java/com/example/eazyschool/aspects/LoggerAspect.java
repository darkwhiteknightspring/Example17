package com.example.eazyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/*

    execution(modifiers-pattern?ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)

 */

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around(value = "execution(* com.example.eazyschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("Executing: " + joinPoint.getSignature().getName() + " execution started");
        Instant start = Instant.now();
        Object return_obj = joinPoint.proceed();
        Instant finish = Instant.now();
        long time = Duration.between(start,finish).toMillis();
        log.info("Time took to execute " + joinPoint.getSignature().toString() + " method is :" + time );
        log.info("Executing: " + joinPoint.getSignature().getName() + " execution finished");
        return return_obj;
    }
    @AfterThrowing(value = "execution(* com.example.eazyschool.*.*(..))",throwing="ex")
    public void logException(JoinPoint joinPoint,Exception ex){
        log.error(joinPoint.getSignature().toString() + "An exception happened due to " + ex.getMessage());
    }
}
