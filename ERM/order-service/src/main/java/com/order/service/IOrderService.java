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

public interface IOrderService {

    Order createOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getOrdersByUserId(Long userId);
}
