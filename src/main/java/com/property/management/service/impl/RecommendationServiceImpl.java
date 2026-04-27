package com.property.management.service.impl;

import com.property.management.dto.PropertyRequest;
import com.property.management.model.Recommendation;
import com.property.management.repository.RecommendationRepository;
import com.property.management.service.RecommendationService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<Recommendation> generateRecommendations(PropertyRequest request) {
        List<Recommendation> budgetFiltered = recommendationRepository.findRecommendationsWithinBudget(
            request.getBudgetMin(), request.getBudgetMax()
        );

        List<Recommendation> typeFiltered = budgetFiltered.stream()
            .filter(r -> r.getPropertyType().equals("ALL") || r.getPropertyType().equals(request.getPropertyType()))
            .collect(Collectors.toList());

        if (request.getPriorityArea() != null && !request.getPriorityArea().isEmpty()) {
            typeFiltered.sort((r1, r2) -> {
                boolean r1Matches = r1.getPriorityTags() != null && r1.getPriorityTags().contains(request.getPriorityArea());
                boolean r2Matches = r2.getPriorityTags() != null && r2.getPriorityTags().contains(request.getPriorityArea());
                return Boolean.compare(r2Matches, r1Matches);
            });
        }

        typeFiltered.sort((r1, r2) -> {
            int impactOrder = getImpactOrder(r2.getImpact()) - getImpactOrder(r1.getImpact());
            if (impactOrder != 0) return impactOrder;
            return Double.compare(r1.getCostMin(), r2.getCostMin());
        });

        return typeFiltered.stream().limit(6).collect(Collectors.toList());
    }

    private int getImpactOrder(String impact) {
        if (impact == null) return 0;
        switch (impact.toUpperCase()) {
            case "HIGH": return 3;
            case "MEDIUM": return 2;
            case "LOW": return 1;
            default: return 0;
        }
    }

    @Override
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public Recommendation addRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation updateRecommendation(Long id, Recommendation recommendation) {
        recommendation.setId(id);
        return recommendationRepository.save(recommendation);
    }

    @Override
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
    }
}