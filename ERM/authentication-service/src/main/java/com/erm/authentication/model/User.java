package com.erm.authentication.model;

import jakarta.persistence.*;
import lombok.NonNull;
import java.time.LocalDateTime;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NonNull String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
