package com.order.service;

import com.order.client.UserServiceClient;
import com.order.client.WebClientConfig;
import com.order.dto.UserDto;
import com.order.model.Order;
import com.order.model.OrderItem;
import com.order.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{
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
        Optional<UserDto> user = Optional.ofNullable(userServiceClient.getUserById(order.getUserId()));
        user.orElseThrow(RuntimeException::new);
        List<OrderItem> updated_order_item = order.getItems().stream()
                .map(item->{
                    item.setOrder_id(order.getId());
                    return item;})
                .toList();
        order.setItems(updated_order_item);
        Order order1 = orderRepository.save(order);

//        // Communication using web client
//        UserDto user2 = webClientConfig.webClientConfigBuilder()
//                .get()
//                .uri("http://localhost:8080/users/1")
//                .retrieve()
//                .bodyToMono(UserDto.class)
//                .block();
//        System.out.println(user2);
        return orderRepository.save(order1);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
