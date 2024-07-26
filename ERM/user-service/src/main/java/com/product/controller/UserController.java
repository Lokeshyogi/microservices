package com.product.controller;

import com.product.model.UserInfo;
import com.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private  final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/name")
    public UserInfo getUserByName(@RequestParam(value = "name") String userName) {
        return userService.findByUsername(userName);
    }
    @GetMapping("/user/email")
    public UserInfo getUserByEmail(@RequestParam(value = "email") String email) {
        return userService.findByEmail(email);
    }

    @PostMapping("/user")
    public UserInfo registerUser(@RequestBody UserInfo userInfo) {
        return userService.registerUser(userInfo);
    }

    @GetMapping("/{id}")
    public UserInfo getUserProfile(@PathVariable Long id) {
        Optional<UserInfo> user1 = userService.findById(id);
        user1.ifPresentOrElse(userInfo -> System.out.println("user exists"),()-> System.out.println("user not found"));
        return user1.orElse(user1.orElseThrow());
    }

    }

