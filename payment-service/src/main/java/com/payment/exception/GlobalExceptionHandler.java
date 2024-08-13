package com.payment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PaymentExceptionErrorResponse> loginException(PaymentServiceException paymentServiceException)
    {
        PaymentExceptionErrorResponse paymentExceptionErrorResponse = new PaymentExceptionErrorResponse();
        paymentExceptionErrorResponse.setMessage(paymentServiceException.getMessage());
        paymentExceptionErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        paymentExceptionErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(paymentExceptionErrorResponse,HttpStatus.UNAUTHORIZED);
    }
}
