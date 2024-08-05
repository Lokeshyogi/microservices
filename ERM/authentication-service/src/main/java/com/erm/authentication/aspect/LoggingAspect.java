package com.erm.authentication.aspect;

import com.erm.authentication.dto.UserDTO;
import com.erm.authentication.model.AuthenticationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.erm.authentication.controller.AuthController.createAuthenticationToken(..))")
    private void forTokenCreationMethod() {
    }
    @Pointcut("execution(* com.erm.authentication.controller.AuthController.registerUser(..))")
    private void forSignUpMethod() {
    }

    @Before("forTokenCreationMethod()")
    public void beforeTokenCreation(JoinPoint theJoinPoint) {
        AuthenticationRequest arg = (AuthenticationRequest) theJoinPoint.getArgs()[0];
        System.out.println(arg.getUsername());
        logger.info(String.format("\n=====>>> Executing creation token process: Username: %s %n", arg.getUsername()));
    }

    //@AfterReturning Advice - Modify Return
    //Value, mean we can modify value which was return, only if execution get success
    @AfterReturning("forTokenCreationMethod()")
    public void afterTokenCreation() {
        logger.info("\n=====>>> Token creation process completed");
    }

    @AfterThrowing(pointcut = "forTokenCreationMethod()", throwing = "theExcep")
    public void afterAnyExceptionTokenCreation(Throwable theExcep) {
        logger.info("=====>>> Token creation process Failed.");
        logger.info(String.format("The exception is: %s", theExcep));
    }


    @Before("forSignUpMethod()")
    public void beforeSignUpMethod(JoinPoint theJoinPoint) {
        UserDTO arg = (UserDTO) theJoinPoint.getArgs()[0];
        System.out.println(arg.getUsername());
        logger.info("\n=====>>> Registration process started =====<<<");
    }
}











