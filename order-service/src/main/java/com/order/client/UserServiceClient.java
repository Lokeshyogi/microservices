package com.order.client;

import com.order.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/users/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
    @GetMapping("/users/user/name")
    UserDto getUserByName(@RequestParam(value = "name") String name);
}

