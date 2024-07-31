//package com.gateway.client;
//
//import com.gateway.model.User;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "user-service")
//public interface UserServiceClient {
//    @GetMapping("/users/user/name")
//    User getUserByName(@RequestParam("name") String name);
//}
//
//
