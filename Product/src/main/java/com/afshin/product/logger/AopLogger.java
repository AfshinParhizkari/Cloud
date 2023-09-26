package com.afshin.product.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLogger {
    @Autowired MyLogger logger;

    @Before("execution(* com.afshin..application..*(..)) ")
    public void logController(JoinPoint joinPoint) {
        String args = argsToString(joinPoint.getArgs());
        String callingMethod = joinPoint.getSignature().getName() + " called :params=" + args;
        logger.eventLogger.info("AOP-BF-CON: "+callingMethod);
    }

    @AfterThrowing(value = "execution(* com.afshin..application..*(..)) ", throwing = "e")
    public void logControllerError(JoinPoint joinPoint, Throwable e) {
        logger.logStackTrace(e);
        logger.eventLogger.error("AOP-EX-CON: "+joinPoint.getSignature().getName() + " error:" + e.getMessage());
    }

    @AfterThrowing(value = "execution(* com.afshin..service..*(..)) ", throwing = "e")
    public void logServiceError(JoinPoint joinPoint, Throwable e) {
        logger.logStackTrace(e);
        logger.eventLogger.error("AOP-EX-SRV: "+joinPoint.getSignature().getName() + " error:" + e.getMessage());
    }

    @AfterReturning(value = "execution(* com.afshin..service..*(..)) ", returning = "returnValue")
    public void returnService(JoinPoint joinPoint, Object returnValue) {
        if (returnValue != null) {
            logger.eventLogger.info("AOP-AF-SRV: "+joinPoint.getSignature().getName() + " returned:" + returnValue);
        } else {
            logger.eventLogger.info("AOP-AF-SRV: "+joinPoint.getSignature().getName() + "Returning null -");
        }
    }

    @AfterReturning(value = "execution(* com.afshin..application..*(..)) ", returning = "returnValue")
    public void returnController(JoinPoint joinPoint, Object returnValue) {
        if (returnValue != null) {
            logger.eventLogger.info("AOP-AF-CON: "+joinPoint.getSignature().getName() + " returned:" + returnValue);
        } else {
            logger.eventLogger.info("AOP-AF-CON: "+joinPoint.getSignature().getName() + "Returning null -");
        }
    }

    private String argsToString(Object[] arguments) {
        String args = "";
        for (Object arg : arguments) {
            if (arg != null)
                args += arg.toString() + ",";
            else
                args += "NULL,";
        }
        if (!"".equals(args))
            args = args.substring(0, args.length() - 1);
        return args;
    }
}