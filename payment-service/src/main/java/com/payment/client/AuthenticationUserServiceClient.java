package com.payment.client;


import com.payment.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-service")
public interface AuthenticationUserServiceClient {
    @GetMapping("/auth/user/name")
    User getUserByName(@RequestParam(value = "name") String name);
}

