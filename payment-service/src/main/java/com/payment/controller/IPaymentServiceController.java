package com.payment.controller;

import com.payment.model.Payment;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public interface IPaymentServiceController {
    Payment createPayment(Payment payment);

    Payment getPaymentByOrderId(Long orderId);

    Payment getPaymentById(Long id);
}
