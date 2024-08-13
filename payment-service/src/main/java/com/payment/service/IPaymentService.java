package com.payment.service;

import com.payment.model.Payment;
import com.payment.model.Status;
import com.payment.repository.PaymentServiceRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface IPaymentService {

    Payment createPayment(Payment payment);

    Payment getPaymentByOrderId(Long orderId);

    Payment getPaymentById(Long id);

    void updatePaymentStatus(Long paymentId, Status status);
}
