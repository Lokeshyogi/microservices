package com.payment.service;

import com.payment.client.OrderServiceClient;
import com.payment.client.AuthenticationUserServiceClient;
import com.payment.client.UserServiceClient;
import com.payment.dto.OrderResponse;
import com.payment.dto.User;
import com.payment.exception.PaymentServiceException;
import com.payment.model.Payment;
import com.payment.model.Status;
import com.payment.repository.PaymentServiceRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentService  implements IPaymentService{

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentServiceRepository paymentRepository;
    private final OrderServiceClient orderServiceClient;
    private final UserServiceClient userServiceClient;


    @Autowired
    public PaymentService(PaymentServiceRepository paymentRepository, EntityManager entityManager, OrderServiceClient orderServiceClient, UserServiceClient userServiceClient) {
        this.paymentRepository = paymentRepository;
        this.orderServiceClient = orderServiceClient;
        this.userServiceClient = userServiceClient;
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        logger.info("Validating the payment is paid or not for a given order");
        Optional<Payment> paymentByOrderId = Optional.ofNullable(getPaymentByOrderId(payment.getOrderId()));
        if(paymentByOrderId.isPresent())
            throw new PaymentServiceException("Amount already paid");
        logger.info("validating the order");
        Optional<OrderResponse> orderById = orderServiceClient.getOrderById(payment.getOrderId());
        orderById.orElseThrow(()->new PaymentServiceException(String.format("Order with order_id: %d not found",payment.getOrderId())));
        logger.info("Validating the amount");
        if(orderById.get().getTotalPrice()>payment.getAmount())
            throw new PaymentServiceException(String.format("Amount of this product is: %1$,.2f",orderById.get().getTotalPrice()));
        payment.setStatus(Status.PENDING);
        logger.info("validating the user");
        Optional<User> userProfile = userServiceClient.getUserProfile(payment.getUserId());
        userProfile.orElseThrow(()->new PaymentServiceException(String.format("User with user_id: %d not found",payment.getUserId())));
        Payment paymentResult = paymentRepository.saveAndFlush(payment);// Ensure the payment is committed to the database
        //Detaching the paymentResult from the persistence context ensures that subsequent updates to the entity do not affect the detached entity
        paymentRepository.detached(paymentResult);
        updatePaymentStatus(paymentResult.getId(), Status.COMPLETED);
        logger.info("updating the order status to COMPLETED");
        orderServiceClient.updateOrderStatus(payment.getOrderId(),"COMPLETED");
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
