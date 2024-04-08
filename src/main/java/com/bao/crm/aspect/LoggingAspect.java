package com.bao.crm.aspect;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger myLogger = LogManager.getLogger(LoggingAspect.class);

    /// execution(public void com.ray.crm.dao.CustomerDAOImpl.deleteCustomer())
    @Pointcut("execution(* com.bao.crm.dao.*.*(..))")
    private void forDaoPackage() {}


    @Before("forDaoPackage()")
    public void beforeLogging(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();

        myLogger.info("===> in @Before adive: calling method: {}", theMethod);
    }

}
