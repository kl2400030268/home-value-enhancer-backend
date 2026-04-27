package com.property.management.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_submissions")
public class PropertySubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "property_type")
    private String propertyType;

    private Integer bhk;
    private Double area;
    private String age;

    @Column(name = "budget_min")
    private Double budgetMin;

    @Column(name = "budget_max")
    private Double budgetMax;

    private String location;

    @Column(name = "condition_status")
    private String conditionStatus;

    @Column(name = "priority_area")
    private String priorityArea;

    @Column(columnDefinition = "TEXT")
    private String recommendations;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    public PropertySubmission() {}

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }
    public Integer getBhk() { return bhk; }
    public void setBhk(Integer bhk) { this.bhk = bhk; }
    public Double getArea() { return area; }
    public void setArea(Double area) { this.area = area; }
    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }
    public Double getBudgetMin() { return budgetMin; }
    public void setBudgetMin(Double budgetMin) { this.budgetMin = budgetMin; }
    public Double getBudgetMax() { return budgetMax; }
    public void setBudgetMax(Double budgetMax) { this.budgetMax = budgetMax; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getConditionStatus() { return conditionStatus; }
    public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
    public String getPriorityArea() { return priorityArea; }
    public void setPriorityArea(String priorityArea) { this.priorityArea = priorityArea; }
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}