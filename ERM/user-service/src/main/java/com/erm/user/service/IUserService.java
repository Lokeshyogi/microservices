package com.erm.user.service;

import com.erm.user.model.User;

import java.util.Optional;

public interface IUserService {
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findById(Long id);
}
