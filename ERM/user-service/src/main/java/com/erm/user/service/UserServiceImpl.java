package com.erm.user.service;

import com.erm.user.model.User;
import com.erm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}

