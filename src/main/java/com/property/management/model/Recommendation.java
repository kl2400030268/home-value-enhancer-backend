package com.property.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recommendations")
public class Recommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    private String category;
    private String costRange;
    private String impact;
    private String timeframe;
    private String priority;
    
    // Default constructor
    public Recommendation() {}
    
    // Parameterized constructor
    public Recommendation(String title, String description, String category, String costRange, 
                          String impact, String timeframe, String priority) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.costRange = costRange;
        this.impact = impact;
        this.timeframe = timeframe;
        this.priority = priority;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getCostRange() { return costRange; }
    public String getImpact() { return impact; }
    public String getTimeframe() { return timeframe; }
    public String getPriority() { return priority; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    public void setCostRange(String costRange) { this.costRange = costRange; }
    public void setImpact(String impact) { this.impact = impact; }
    public void setTimeframe(String timeframe) { this.timeframe = timeframe; }
    public void setPriority(String priority) { this.priority = priority; }
}