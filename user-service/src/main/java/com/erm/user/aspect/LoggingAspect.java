package com.erm.user.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.erm.user.controller.UserController.getUserByName(..))")
    private void forGetUserByName() {
    }
    @Pointcut("execution(* com.erm.user.controller.UserController.getUserByEmail(..))")
    private void forGetUserByEmail() {
    }

    @Before("forGetUserByName()")
    public void beforeGetUserByName(JoinPoint theJoinPoint) {
        String name = (String) theJoinPoint.getArgs()[0];
        logger.info("\n=====>>> Getting user: {}",name);
    }

    @AfterReturning("forGetUserByName()")
    public void beforeGetUserByName() {
        logger.info("\n=====>>> Get user completed");
    }

    @Before("forGetUserByEmail()")
    public void beforeGetUserByEmail(JoinPoint theJoinPoint) {
        String email = (String) theJoinPoint.getArgs()[0];
        logger.info("\n=====>>> Getting user by mail: {}",email);
    }

    @AfterReturning("forGetUserByEmail()")
    public void beforeGetUserByEmail() {
        logger.info("\n=====>>> Get user completed from mail");
    }


}











