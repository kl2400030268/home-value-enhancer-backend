package com.property.management.controller;

import com.property.management.model.Property;
import com.property.management.repository.PropertyRepository;
import com.property.management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5500"})
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private PropertyService propertyService;

    // GET all properties - this is called by getAllProperties()
    @GetMapping("/properties")
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // GET single property
    @GetMapping("/properties/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    // CREATE property
    @PostMapping("/properties")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property saved = propertyService.createProperty(property);
        return ResponseEntity.ok(saved);
    }

    // UPDATE property
    @PutMapping("/properties/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        Property updated = propertyService.updateProperty(id, property);
        return ResponseEntity.ok(updated);
    }

    // DELETE property
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Property deleted successfully");
        return ResponseEntity.ok(response);
    }
    
    // This endpoint returns data for the dashboard's "Popular Enhancement Ideas"
    // Your UserDashboard calls getAllProperties() which uses /properties endpoint
    // So we need to make sure /properties returns data that matches what your dashboard expects
}