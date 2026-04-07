package com.property.management.config;

import com.property.management.model.Recommendation;
import com.property.management.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only add if database is empty
        if (recommendationRepository.count() == 0) {
            
            Recommendation rec1 = new Recommendation(
                "Modern Kitchen Renovation",
                "Upgrade your kitchen with modular cabinets, granite countertops, and energy-efficient appliances",
                "Kitchen",
                "₹1,00,000 - ₹2,50,000",
                "High - Increases property value by 15-20%",
                "2-3 weeks",
                "High"
            );
            
            Recommendation rec2 = new Recommendation(
                "Bathroom Remodeling",
                "Install modern fixtures, waterproofing, and elegant tiles for a spa-like experience",
                "Bathroom",
                "₹50,000 - ₹1,50,000",
                "Medium - Improves daily living experience",
                "1-2 weeks",
                "High"
            );
            
            Recommendation rec3 = new Recommendation(
                "Flooring Upgrade",
                "Replace old flooring with vitrified tiles or hardwood for a premium look",
                "Flooring",
                "₹1,50,000 - ₹3,00,000",
                "High - Dramatically improves aesthetics",
                "1-2 weeks",
                "Medium"
            );
            
            Recommendation rec4 = new Recommendation(
                "Smart Home Automation",
                "Install smart lighting, AC controls, and security systems",
                "Technology",
                "₹75,000 - ₹2,00,000",
                "Medium - Modern convenience",
                "1 week",
                "Low"
            );
            
            Recommendation rec5 = new Recommendation(
                "Exterior Painting",
                "Fresh coat of weather-resistant paint to enhance curb appeal",
                "Exterior",
                "₹80,000 - ₹2,00,000",
                "High - First impression matters",
                "1 week",
                "Medium"
            );
            
            Recommendation rec6 = new Recommendation(
                "Landscaping & Garden",
                "Create a beautiful garden with plants, lighting, and seating areas",
                "Exterior",
                "₹50,000 - ₹1,50,000",
                "Medium - Improves outdoor living",
                "2 weeks",
                "Low"
            );
            
            recommendationRepository.save(rec1);
            recommendationRepository.save(rec2);
            recommendationRepository.save(rec3);
            recommendationRepository.save(rec4);
            recommendationRepository.save(rec5);
            recommendationRepository.save(rec6);
            
            System.out.println("Sample recommendations loaded!");
        }
    }
}