package com.erm.user.service;

import com.erm.user.dto.UserDTO;
import com.erm.user.exception.UserException;
import com.erm.user.model.User;
import com.erm.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> user;
        try {
             user= userRepository.findByUsername(username);
        }
        catch (Exception e)
        {
            logger.info("Error while fetching user by name: {}",username);
            throw new UserException(e.getMessage(),e.getCause());
        }
        return user;
    }
    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user;
        try {
            user= userRepository.findByEmail(email);
        }
        catch (Exception e)
        {
            logger.info("Error while fetching user by email: {}",email);
            throw new UserException(e.getMessage(),e.getCause());
        }
        return user;
    }
    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user;
        try {
            user= userRepository.findById(id);
        }
        catch (Exception e)
        {
            logger.info("Error while fetching user by id: {}",id);
            throw new UserException(e.getMessage(),e.getCause());
        }
        return user;
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Optional<User> user1 = findById(id);
        user1.orElseThrow(()->new UserException("user not found"));
        User user = user1.get();
        modelMapper.map(userDTO,user);
        return userRepository.save(user);
    }
}

