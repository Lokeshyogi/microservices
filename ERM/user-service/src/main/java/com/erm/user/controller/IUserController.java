package com.erm.user.controller;

import com.erm.user.dto.UserDTO;
import com.erm.user.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserController {
    public User getUserByName(String name);
    public User getUserByEmail(String email);
    public User getUserProfile(Long id);
    public ResponseEntity<User> updateUser(Long id, UserDTO userDTO);
}
