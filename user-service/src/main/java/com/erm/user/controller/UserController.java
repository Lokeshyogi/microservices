package com.erm.user.controller;

import com.erm.user.dto.UserDTO;
import com.erm.user.exception.UserException;
import com.erm.user.service.UserServiceImpl;
import com.erm.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/name")
    public ResponseEntity<User> getUserByName(@RequestParam(value = "name") String userName) {
        Optional<User> user1 = userService.findByUsername(userName);
        user1.orElseThrow(() -> new UserException("user not found"));
        return new ResponseEntity<>(user1.get(), HttpStatus.OK);
    }

    @GetMapping("/user/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam(value = "email") String email) {
        Optional<User> user1 = userService.findByEmail(email);
        user1.orElseThrow(() -> new UserException("user not found"));
        return new ResponseEntity<>(user1.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        Optional<User> user1 = userService.findById(id);
        user1.orElseThrow(() -> new UserException("user not found"));
        return new ResponseEntity<>(user1.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

}

