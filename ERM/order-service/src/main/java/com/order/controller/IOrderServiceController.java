package com.order.controller;

import com.order.client.UserServiceClient;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOrderServiceController {

    Order createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getOrdersByUserId(Long userId);

}
