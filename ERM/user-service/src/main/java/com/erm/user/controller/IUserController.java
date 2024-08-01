package com.erm.user.controller;

import com.erm.user.model.User;

public interface IUserController {
    public User getUserByName(String name);
    public User getUserByEmail(String email);
    public User getUserProfile(Long id);
}
