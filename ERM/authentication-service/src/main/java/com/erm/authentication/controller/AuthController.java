package com.erm.authentication.controller;

import com.erm.authentication.Excepetion.UserAuthenticationException;
import com.erm.authentication.model.AuthenticationRequest;
import com.erm.authentication.model.AuthenticationResponse;
import com.erm.authentication.model.User;
import com.erm.authentication.repository.UserRepository;
import com.erm.authentication.service.CustomUserDetailsService;
import com.erm.authentication.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

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

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new UserAuthenticationException("Incorrect username or password", e.getCause());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        User userInfo1 = new User();
        String encode = passwordEncoder.encode(user.getPassword());
        userInfo1.setUsername(user.getUsername());
        userInfo1.setPassword(encode);
        userInfo1.setEmail(user.getEmail());
        userRepository.save(userInfo1);
        return ResponseEntity.ok("User registered successfully.");
    }

    @GetMapping("/user/name")
    public User getUserByName(@RequestParam(value = "name") String userName) {
        return userRepository.findByUsername(userName);
    }

}
