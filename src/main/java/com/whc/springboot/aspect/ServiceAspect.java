package com.whc.springboot.aspect;

import com.whc.springboot.interceptor.RequestViewInterceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ClassName: ServiceAspect <br/>
 * Description: <br/>
 * date: 2020/8/19 16:13<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Aspect
@Component
public class ServiceAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestViewInterceptor.class);

    @Pointcut("@annotation(com.whc.springboot.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){}
    @Before(value = "com.whc.springboot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeService(JoinPoint joinpoint) {
        LOGGER.debug("==== This is before service ====");
    }

    @Around(value = "com.whc.springboot.aspect.ControllerAspect.controllerPointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LOGGER.debug("==== This is around service ==== ");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.whc.springboot.aspect.ControllerAspect.controllerPointCut()")
    public void afterService() {
        LOGGER.debug("==== This is after service ====");
    }
}
