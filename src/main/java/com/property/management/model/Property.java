package com.property.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String propertyType;
    private int bhk;
    private int area;
    private int age;
    private int budget;
    private String location;
    
    @Column(name = "property_condition")  // Changed from "condition"
    private String condition;
    
    private String priority;
    
    @Column(name = "user_id")
    private Long userId;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPropertyType() { return propertyType; }
    public void setPropertyType(String propertyType) { this.propertyType = propertyType; }
    
    public int getBhk() { return bhk; }
    public void setBhk(int bhk) { this.bhk = bhk; }
    
    public int getArea() { return area; }
    public void setArea(int area) { this.area = area; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public int getBudget() { return budget; }
    public void setBudget(int budget) { this.budget = budget; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}