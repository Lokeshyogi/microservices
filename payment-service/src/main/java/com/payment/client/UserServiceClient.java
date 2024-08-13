package com.payment.client;

import com.payment.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("user-service")
public interface UserServiceClient {
    @GetMapping("/users/{id}")
    Optional<User> getUserProfile(@PathVariable Long id);
}
