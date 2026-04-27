package com.property.management.dto;

import jakarta.validation.constraints.*;

public class PropertyRequest {

    @NotBlank(message = "Property type is required")
    private String propertyType;

    @NotNull(message = "BHK is required")
    @Min(1)
    @Max(5)
    private Integer bhk;

    @NotNull(message = "Area is required")
    @Positive
    private Double area;

    @NotBlank(message = "Property age is required")
    private String age;

    @NotNull(message = "Minimum budget is required")
    @Positive
    private Double budgetMin;

    @NotNull(message = "Maximum budget is required")
    @Positive
    private Double budgetMax;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Condition is required")
    private String conditionStatus;

    private String priorityArea;

    public PropertyRequest() {}

    // Getters and Setters
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
}