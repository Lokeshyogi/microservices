package com.erm.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> loginException(UserException userException)
    {
        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setMessage(userException.getMessage());
        userErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        userErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse,HttpStatus.UNAUTHORIZED);
    }
}
