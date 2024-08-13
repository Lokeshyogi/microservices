//package com.erm.authentication.aspect;
//
//import com.erm.authentication.Excepetion.AuthErrorResponse;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LearningLoggingAspect {
//
//
////    @Order: to apply order
//    //declare pointcut
//
//    @Pointcut("execution(* com.erm.authentication.controller.AuthController.createAuthenticationToken(..))")
//    private void forTokenCreationMethod() {
//    }
//
//    // this is where we add all of our related advices for logging
//
//    // let's start with a @Before advice
////    @Before("forTokenCreationMethod()")
////    public void beforeTokenCreation(JoinPoint theJoinPoint) {
////        Object[] args = theJoinPoint.getArgs();
////        Object arg = args[0];
////        System.out.println(arg);
////        System.out.printf("\n=====>>> Executing creation token process: Username: test %n");
////    }
//
//    //@AfterReturning Advice - Modify Return
//    //Value, mean we can modify value which was return, only if execution get success
//    @AfterReturning("forTokenCreationMethod()")
//    public void afterTokenCreation() {
//        System.out.println("\n=====>>> Token creation process completed");
//    }
//
//    @AfterThrowing(pointcut = "forTokenCreationMethod()", throwing = "theExcep")
//    public void afterAnyExceptionTokenCreation(Throwable theExcep) {
//        System.out.println("\n=====>>> Token creation process Failed.");
//        System.out.println("\n=====>>> The exception is: " + theExcep);
//    }
//
//
//    //@After, runs after a method completed, regardless of failed or successful execution
//
////    @After("forTokenCreationMethod()")
////    public void afterFinallyTokenCreation() {
////        System.out.println("Executing @After (finally) advice");
////    }
//
////    @Around: Like a combination of @Before and @After
////    But gives you more fine-grained control
//
////    @Around("forTokenCreationMethod()")
////    public Object afterTokenCreationMethod(
////            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
////// get begin timestamp
////        long begin = System.currentTimeMillis();
////// now, let's execute the method
////        Object result = theProceedingJoinPoint.proceed();
////// get end timestamp
////        long end = System.currentTimeMillis();
////// compute duration and display it
////        long duration = end - begin;
////        System.out.println("\n=====> Duration: " + duration + " milliseconds");
////        return result;
////    }
//
//
//    @Around("forTokenCreationMethod()")
//    public Object afterTokenCreationMethodException(
//            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
//        Object result = null;
//        try {
//// let's execute the method
//            result = theProceedingJoinPoint.proceed();
//            return result;
//        } catch (Exception exc) {
//// log exception
//            System.out.println("@Around advice: We have a problem " + exc);
//// handle and give default fortune ... use this approach with caution
//            result = "Nothing exciting here. Move along!";
//            //either we can rethrow the exception
//            AuthErrorResponse authErrorResponse = new AuthErrorResponse();
//            authErrorResponse.setMessage(exc.getMessage());
//            authErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//            authErrorResponse.setTimeStamp(System.currentTimeMillis());
//            return new ResponseEntity<>(authErrorResponse, HttpStatus.UNAUTHORIZED);
//        }
////        return ResponseEntity.ok(result) ;
//
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
