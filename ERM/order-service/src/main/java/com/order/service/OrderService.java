package com.order.service;

import com.order.model.Order;
import com.order.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderServiceRepository orderRepository;


    @Autowired
    public OrderService(OrderServiceRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {


        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
