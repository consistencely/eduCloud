package com.xuegao.educloud.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;

/**
 * @Auther: LIM
 * @Date: 2019/11/22 14:07
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class ControllerResponseAspect {

    @Pointcut(value = "execution(public * com.xuegao.educloud..service.impl.*Service(..))")
    public void cutController(){

    }

    @Before("cutController()")
    public void beforeController(JoinPoint joinPoint){
        log.info("方法执行前");
    }

    @After("cutController()")
    public void afterController(JoinPoint joinPoint){
        log.info("方法执行后");
    }

    @AfterReturning(pointcut="cutController()",returning = "rst")
    public void afterRunning(Response rst){
        log.info(rst.toString());
    }

}
