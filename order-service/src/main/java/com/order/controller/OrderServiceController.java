package com.order.controller;

import com.order.client.UserServiceClient;
import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.dto.UserDto;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderServiceController implements IOrderServiceController {

    private final OrderService orderService;
    private final UserServiceClient userServiceClient;

    @Autowired
    public OrderServiceController(OrderService orderService, UserServiceClient userServiceClient) {
        this.orderService = orderService;
        this.userServiceClient = userServiceClient;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/{id}")
    public OrderResponse updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id,status);
    }

}
