package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private  final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String userName) {
        return userService.findByUsername(userName);
    }
//    @GetMapping("/user/{email}")
//    public User getUser(@PathVariable("username") String userName) {
//        return userService.findByUsername(userName);
//    }

    }

