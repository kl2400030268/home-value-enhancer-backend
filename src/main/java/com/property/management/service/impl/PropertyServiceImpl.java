package com.property.management.service.impl;

import com.property.management.exception.ResourceNotFoundException;
import com.property.management.model.Property;
import com.property.management.repository.PropertyRepository;
import com.property.management.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
    }

    @Override
    public Property updateProperty(Long id, Property property) {
        Property existing = getPropertyById(id);
        
        // Update with the correct field names from your Property class
        existing.setPropertyType(property.getPropertyType());
        existing.setBhk(property.getBhk());
        existing.setArea(property.getArea());
        existing.setAge(property.getAge());
        existing.setBudget(property.getBudget());
        existing.setLocation(property.getLocation());
        existing.setCondition(property.getCondition());
        existing.setPriority(property.getPriority());
        existing.setUserId(property.getUserId());
        
        return propertyRepository.save(existing);
    }

    @Override
    public void deleteProperty(Long id) {
        Property existing = getPropertyById(id);
        propertyRepository.delete(existing);
    }
}