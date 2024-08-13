package com.erm.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<InventoryExceptionErrorResponse> loginException(InventoryServiceException inventoryServiceException)
    {
        InventoryExceptionErrorResponse inventoryExceptionErrorResponse = new InventoryExceptionErrorResponse();
        inventoryExceptionErrorResponse.setMessage(inventoryServiceException.getMessage());
        inventoryExceptionErrorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        inventoryExceptionErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(inventoryExceptionErrorResponse,HttpStatus.UNAUTHORIZED);
    }
}
