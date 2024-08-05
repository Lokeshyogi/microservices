package com.erm.authentication.controller;

import com.erm.authentication.aspect.LoggingAspect;
import com.erm.authentication.dto.UserDTO;
import com.erm.authentication.excepetion.UserAuthenticationException;
import com.erm.authentication.model.AuthenticationRequest;
import com.erm.authentication.model.AuthenticationResponse;
import com.erm.authentication.model.User;
import com.erm.authentication.repository.UserRepository;
import com.erm.authentication.service.CustomUserDetailsService;
import com.erm.authentication.service.JwtUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            logger.info("Incorrect username or password");
            throw new UserAuthenticationException("Incorrect username or password", e.getCause());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            logger.info("username not available");
            throw new UserAuthenticationException("username not available");
        }
        String encode = passwordEncoder.encode(userDTO.getPassword());
        User user = new User();
        modelMapper.map(userDTO, user);
        user.setPassword(encode);
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        }
        catch (Exception e)
        {
            logger.info("error while register: {}",e.getMessage());
            throw new UserAuthenticationException(e.getMessage(),e.getCause());
        }
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/user/name")
    public User getUserByName(@RequestParam(value = "name") String userName) {
        return userRepository.findByUsername(userName);
    }

}
