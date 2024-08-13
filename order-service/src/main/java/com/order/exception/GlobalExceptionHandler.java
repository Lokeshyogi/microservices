package com.order.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderExceptionErrorResponse> loginException(OrderServiceException orderServiceException)
    {
        OrderExceptionErrorResponse orderExceptionErrorResponse = new OrderExceptionErrorResponse();
        orderExceptionErrorResponse.setMessage(orderServiceException.getMessage());
        orderExceptionErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        orderExceptionErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(orderExceptionErrorResponse,HttpStatus.UNAUTHORIZED);
    }
}
