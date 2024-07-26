package com.product.repository;


import com.product.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderServiceRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
