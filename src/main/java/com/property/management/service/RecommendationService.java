package com.property.management.service;

import com.property.management.dto.PropertyRequest;
import com.property.management.model.Recommendation;
import java.util.List;

public interface RecommendationService {
    List<Recommendation> generateRecommendations(PropertyRequest request);
    List<Recommendation> getAllRecommendations();
    Recommendation addRecommendation(Recommendation recommendation);
    Recommendation updateRecommendation(Long id, Recommendation recommendation);
    void deleteRecommendation(Long id);
}