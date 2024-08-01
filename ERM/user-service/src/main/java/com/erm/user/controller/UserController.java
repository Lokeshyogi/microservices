package com.erm.user.controller;

import com.erm.user.service.UserServiceImpl;
import com.erm.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {
    private  final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/name")
    public User getUserByName(@RequestParam(value = "name") String userName) {
        return userService.findByUsername(userName);
    }
    @GetMapping("/user/email")
    public User getUserByEmail(@RequestParam(value = "email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/{id}")
    public User getUserProfile(@PathVariable Long id) {
        Optional<User> user1 = userService.findById(id);
        user1.ifPresentOrElse(user -> System.out.println("user exists"),()-> System.out.println("user not found"));
        return user1.orElse(user1.orElseThrow());
    }

}

