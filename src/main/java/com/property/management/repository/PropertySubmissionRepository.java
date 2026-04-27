package com.property.management.repository;

import com.property.management.model.PropertySubmission;
import com.property.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertySubmissionRepository extends JpaRepository<PropertySubmission, Long> {
    List<PropertySubmission> findByUserOrderBySubmittedAtDesc(User user);
    List<PropertySubmission> findByUser_IdOrderBySubmittedAtDesc(Long userId);
    List<PropertySubmission> findAllByOrderBySubmittedAtDesc();
}