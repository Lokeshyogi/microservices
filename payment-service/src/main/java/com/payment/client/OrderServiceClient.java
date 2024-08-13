package com.payment.client;

import com.payment.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "order-service")
public interface OrderServiceClient {
    @GetMapping("/orders/{id}")
    Optional<OrderResponse> getOrderById(@PathVariable("id") Long id);
    @PutMapping("/orders/{id}")
    Optional<OrderResponse> updateOrderStatus(@PathVariable("id") Long id, @RequestParam String status);
}
