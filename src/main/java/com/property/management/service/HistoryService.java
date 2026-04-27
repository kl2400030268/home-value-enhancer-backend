package com.property.management.service;

import com.property.management.model.PropertySubmission;
import com.property.management.model.Recommendation;
import com.property.management.model.User;
import java.util.List;
import java.util.Map;

public interface HistoryService {
    PropertySubmission saveSubmission(User user, Map<String, Object> propertyData, List<Recommendation> recommendations);
    List<PropertySubmission> getUserHistory(User user);
    void deleteSubmission(Long id, User user);
}