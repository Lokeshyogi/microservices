package com.erm.user.controller;

import com.erm.user.dto.UserDTO;
import com.erm.user.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserController {
    public ResponseEntity<User> getUserByName(String name);
    public ResponseEntity<User> getUserByEmail(String email);
    public ResponseEntity<User> getUserProfile(Long id);
    public ResponseEntity<User> updateUser(Long id, UserDTO userDTO);
}
