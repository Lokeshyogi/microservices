package com.payment.exception;

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException() {
        super();
    }

    public PaymentServiceException(String message) {
        super(message);
    }

    public PaymentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentServiceException(Throwable cause) {
        super(cause);
    }

    protected PaymentServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
