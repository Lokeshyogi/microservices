package com.payment.service;

import com.payment.model.Payment;
import com.payment.model.Status;
import com.payment.repository.PaymentServiceRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentServiceRepository paymentRepository;


    @Autowired
    public PaymentService(PaymentServiceRepository paymentRepository, EntityManager entityManager) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        payment.setStatus(Status.PENDING);
        Payment paymentResult = paymentRepository.saveAndFlush(payment);// Ensure the payment is committed to the database
        //Detaching the paymentResult from the persistence context ensures that subsequent updates to the entity do not affect the detached entity
        paymentRepository.detached(paymentResult);
        updatePaymentStatus(paymentResult.getId(), Status.COMPLETED);
        return paymentResult;
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Transactional
    public void updatePaymentStatus(Long paymentId, Status status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(status);
        paymentRepository.save(payment);
    }
}
