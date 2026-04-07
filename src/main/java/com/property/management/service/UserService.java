package com.property.management.service;

import com.property.management.model.User;

public interface UserService {
    User register(User user);
    User login(String email, String password);
}