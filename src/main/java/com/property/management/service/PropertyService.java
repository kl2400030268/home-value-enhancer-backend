package com.property.management.service;

import com.property.management.model.Property;

import java.util.List;

public interface PropertyService {
    Property createProperty(Property property);
    List<Property> getAllProperties();
    Property getPropertyById(Long id);
    Property updateProperty(Long id, Property property);
    void deleteProperty(Long id);
}