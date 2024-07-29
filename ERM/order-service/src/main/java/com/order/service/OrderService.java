package com.order.service;

import com.order.client.UserServiceClient;
import com.order.client.WebClientConfig;
import com.order.dto.UserDto;
import com.order.model.Order;
import com.order.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderServiceRepository orderRepository;
    private final WebClientConfig webClientConfig;
    private final UserServiceClient userServiceClient;


    @Autowired
    public OrderService(OrderServiceRepository orderRepository, WebClientConfig webClientConfig, UserServiceClient userServiceClient) {
        this.orderRepository = orderRepository;
        this.webClientConfig = webClientConfig;
        this.userServiceClient = userServiceClient;
    }

    public Order createOrder(Order order) {
        // Communication using feign client
        UserDto user = userServiceClient.getUserById(order.getUserId());
        System.out.println(user);
        // Communication using web client
        UserDto user2 = webClientConfig.webClientConfigBuilder()
                .get()
                .uri("http://localhost:8080/users/1")
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
        System.out.println(user2);
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
