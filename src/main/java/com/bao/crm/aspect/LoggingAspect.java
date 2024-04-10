package com.bao.crm.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.bao.crm.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.bao.crm..controller.*.*(..))")
        private void forControllerPackage() {
        }

    @Pointcut("execution(* com.bao.crm.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forServicePackage() || forDaoPackage() || forControllerPackage()")
    private void forLoggingAscpect() {}

    @Before("forLoggingAscpect()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();

        Object[] agrs = joinPoint.getArgs();

        logger.info("===> in @Before adive : calling method" + method);

        for(Object a : agrs){
            logger.info("===> agrument : " + a);
        }
    }

    @AfterReturning(pointcut = "forLoggingAscpect()" , returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().toShortString();

        logger.info("===> in @AfterReturning adive : calling method" + method);

        logger.info("===> result : " + result);
    }
}
