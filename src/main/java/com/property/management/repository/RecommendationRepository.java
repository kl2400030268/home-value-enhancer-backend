package com.property.management.repository;

import com.property.management.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    @Query("SELECT r FROM Recommendation r WHERE r.minBudget <= :budgetMax AND r.maxBudget >= :budgetMin")
    List<Recommendation> findRecommendationsWithinBudget(@Param("budgetMin") Double budgetMin, @Param("budgetMax") Double budgetMax);
}