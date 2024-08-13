package com.order.controller;

import com.order.client.UserServiceClient;
import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IOrderServiceController {

    OrderResponse createOrder(OrderRequest orderRequest);

    Order getOrderById(Long id);

    List<Order> getOrdersByUserId(Long userId);

    OrderResponse updateOrderStatus( Long id, String status);

}
