package com.property.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeValueEnhancerBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeValueEnhancerBackendApplication.class, args);
        System.out.println("========================================");
        System.out.println("Home Value Enhancer Backend Started!");
        System.out.println("Server running on: http://localhost:8080");
        System.out.println("========================================");
    }
}