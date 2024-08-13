package com.erm.user.client;


import com.erm.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-service")
public interface UserServiceClient {
    @GetMapping("/auth/user/name")
    User getUserByName(@RequestParam(value = "name") String name);
}

