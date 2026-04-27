package com.property.management.service.impl;

import com.property.management.dto.AuthResponse;
import com.property.management.dto.LoginRequest;
import com.property.management.dto.RegisterRequest;
import com.property.management.model.User;
import com.property.management.exception.BadRequestException;
import com.property.management.repository.UserRepository;
import com.property.management.service.AuthService;
import com.property.management.util.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Map<String, String> captchaStore = new HashMap<>();

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;

        // dummy captchas
        captchaStore.put("G79SZX", "G79SZX");
        captchaStore.put("5K6AS7QP", "5K6AS7QP");
    }

    // ✅ REGISTER
    @Override
    public AuthResponse register(RegisterRequest request) {

        if (request.getCaptcha() == null || !captchaStore.containsKey(request.getCaptcha())) {
            throw new BadRequestException("Invalid CAPTCHA");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("Email is already registered");
        }

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(email);
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getEmail());

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getRole(),
                token,
                "User registered successfully"
        );
    }

    // ✅ LOGIN
    @Override
    public AuthResponse login(LoginRequest request) {

        if (request.getCaptcha() == null || !captchaStore.containsKey(request.getCaptcha())) {
            throw new BadRequestException("Invalid CAPTCHA");
        }

        String email = request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // ✅ FIXED: create token properly
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                token,
                "Login successful"
        );
    }

    // ✅ ADMIN LOGIN
    @Override
    public AuthResponse adminLogin(LoginRequest request) {

        AuthResponse response = login(request);

        if (!"ADMIN".equals(response.getRole())) {
            throw new BadRequestException("Access denied. Admin only.");
        }

        return response;
    }
}