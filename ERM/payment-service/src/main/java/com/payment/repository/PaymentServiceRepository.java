package com.payment.repository;

import com.payment.model.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentServiceRepository extends JpaRepository<Payment, Long>,PaymentServiceRepositoryCustom {
    Payment findByOrderId(Long orderId);
}

 interface PaymentServiceRepositoryCustom {
    void refresh(Payment payment);
    void detached(Payment payment);
}

 class PaymentServiceRepositoryCustomImpl implements PaymentServiceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void refresh(Payment entity) {
        entityManager.refresh(entity);
    }
     @Override
     public void detached(Payment entity) {
         entityManager.detach(entity);
     }
}