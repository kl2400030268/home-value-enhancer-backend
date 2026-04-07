package com.property.management.controller;

import com.property.management.model.User;
import com.property.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")  // Different path for admin
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5500"})
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Admin login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            
            // Check if user has admin role
            if (!"ADMIN".equals(user.getRole())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Access denied. Admin privileges required.");
                return ResponseEntity.status(403).body(error);
            }
            
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password");
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Admin login successful");
            response.put("token", "admin-token-" + System.currentTimeMillis());
            response.put("accessToken", "admin-access-" + System.currentTimeMillis());
            response.put("role", "ADMIN");
            
            Map<String, Object> adminData = new HashMap<>();
            adminData.put("id", user.getId());
            adminData.put("name", user.getName());
            adminData.put("email", user.getEmail());
            response.put("admin", adminData);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid admin credentials");
            return ResponseEntity.status(401).body(error);
        }
    }
}