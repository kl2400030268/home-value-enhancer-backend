package com.property.management.service;

import com.property.management.dto.AuthResponse;
import com.property.management.dto.LoginRequest;
import com.property.management.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse adminLogin(LoginRequest request);
}