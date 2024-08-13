package com.product.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductExceptionErrorResponse> loginException(ProductServiceException productServiceException)
    {
        ProductExceptionErrorResponse productExceptionErrorResponse = new ProductExceptionErrorResponse();
        productExceptionErrorResponse.setMessage(productServiceException.getMessage());
        productExceptionErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        productExceptionErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(productExceptionErrorResponse,HttpStatus.UNAUTHORIZED);
    }
}
