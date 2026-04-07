package com.property.management.controller;

import com.property.management.model.User;
import com.property.management.repository.UserRepository;
import com.property.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")  // For regular users
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5500"})
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    // Get all users (admin only)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Regular user login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password");
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", "token-" + System.currentTimeMillis());
            response.put("accessToken", "access-" + System.currentTimeMillis());
            response.put("role", user.getRole() != null ? user.getRole() : "USER");
            
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", user.getId());
            userData.put("name", user.getName());
            userData.put("email", user.getEmail());
            userData.put("phone", user.getPhone() != null ? user.getPhone() : "");
            response.put("user", userData);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("error", "Invalid email or password");
            return ResponseEntity.status(401).body(error);
        }
    }

    // Regular user registration
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Email already exists");
                error.put("error", "Email already registered");
                return ResponseEntity.badRequest().body(error);
            }
            
            // Set default role for regular users
            user.setRole("USER");
            
            User savedUser = userRepository.save(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("token", "token-" + System.currentTimeMillis());
            response.put("accessToken", "access-" + System.currentTimeMillis());
            response.put("role", "USER");
            
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", savedUser.getId());
            userData.put("name", savedUser.getName());
            userData.put("email", savedUser.getEmail());
            userData.put("phone", savedUser.getPhone() != null ? savedUser.getPhone() : "");
            response.put("user", userData);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Registration failed: " + e.getMessage());
            error.put("error", "Registration failed");
            return ResponseEntity.badRequest().body(error);
        }
    }
}