package com.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String username;
    @NonNull
    @Column(nullable = false)
    private String password;
    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();

}
