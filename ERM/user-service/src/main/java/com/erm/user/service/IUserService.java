package com.erm.user.service;

import com.erm.user.dto.UserDTO;
import com.erm.user.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    User updateUser(Long id, UserDTO userDTO);
}
