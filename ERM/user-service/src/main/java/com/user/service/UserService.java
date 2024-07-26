package com.user.service;

import com.user.model.UserInfo;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo registerUser(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    public UserInfo findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserInfo findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<UserInfo> findById(Long id) {
        return userRepository.findById(id);
    }
}

