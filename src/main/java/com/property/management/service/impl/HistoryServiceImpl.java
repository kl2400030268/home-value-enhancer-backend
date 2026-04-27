package com.property.management.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.property.management.model.PropertySubmission;
import com.property.management.model.Recommendation;
import com.property.management.model.User;
import com.property.management.repository.PropertySubmissionRepository;
import com.property.management.service.HistoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final PropertySubmissionRepository submissionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HistoryServiceImpl(PropertySubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public PropertySubmission saveSubmission(User user, Map<String, Object> propertyData, List<Recommendation> recommendations) {
        PropertySubmission submission = new PropertySubmission();
        submission.setUser(user);
        submission.setPropertyType((String) propertyData.get("propertyType"));
        submission.setBhk((Integer) propertyData.get("bhk"));
        submission.setArea((Double) propertyData.get("area"));
        submission.setAge((String) propertyData.get("age"));
        submission.setBudgetMin((Double) propertyData.get("budgetMin"));
        submission.setBudgetMax((Double) propertyData.get("budgetMax"));
        submission.setLocation((String) propertyData.get("location"));
        submission.setConditionStatus((String) propertyData.get("conditionStatus"));
        submission.setPriorityArea((String) propertyData.get("priorityArea"));

        try {
            String recommendationsJson = objectMapper.writeValueAsString(recommendations);
            submission.setRecommendations(recommendationsJson);
        } catch (Exception e) {
            submission.setRecommendations("[]");
        }

        return submissionRepository.save(submission);
    }

    @Override
    public List<PropertySubmission> getUserHistory(User user) {
        return submissionRepository.findByUserOrderBySubmittedAtDesc(user);
    }

    @Override
    public void deleteSubmission(Long id, User user) {
        PropertySubmission submission = submissionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Submission not found"));

        if (!submission.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this submission");
        }

        submissionRepository.delete(submission);
    }
}