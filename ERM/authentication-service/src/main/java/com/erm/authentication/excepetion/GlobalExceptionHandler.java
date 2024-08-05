package com.erm.authentication.excepetion;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AuthErrorResponse> loginException(UserAuthenticationException userAuthenticationException)
    {
        AuthErrorResponse authErrorResponse = new AuthErrorResponse();
        authErrorResponse.setMessage(userAuthenticationException.getMessage());
        authErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        authErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(authErrorResponse,HttpStatus.UNAUTHORIZED);
    }
}
