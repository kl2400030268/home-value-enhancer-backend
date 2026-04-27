package com.property.management.controller;

import com.property.management.dto.ApiResponse;
import com.property.management.model.User;
import com.property.management.model.PropertySubmission;
import com.property.management.repository.UserRepository;
import com.property.management.repository.PropertySubmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertySubmissionRepository propertySubmissionRepository;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(new ApiResponse(true, "Users fetched successfully", users));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        return ResponseEntity.ok(new ApiResponse(true, "User fetched successfully", user));
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<ApiResponse> updateUserRole(@PathVariable Long id,
                                                      @RequestParam String role) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRole(role);
            userRepository.save(user);
        }
        return ResponseEntity.ok(new ApiResponse(true, "User role updated successfully", user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "User deleted successfully", null));
    }

    @GetMapping("/submissions")
    public ResponseEntity<ApiResponse> getAllSubmissions() {
        List<PropertySubmission> submissions = propertySubmissionRepository.findAll();
        return ResponseEntity.ok(new ApiResponse(true, "Submissions fetched successfully", submissions));
    }

    @GetMapping("/submissions/user/{userId}")
    public ResponseEntity<ApiResponse> getUserSubmissions(@PathVariable Long userId) {
        List<PropertySubmission> submissions = propertySubmissionRepository.findByUser_IdOrderBySubmittedAtDesc(userId);
        return ResponseEntity.ok(new ApiResponse(true, "User submissions fetched successfully", submissions));
    }
}