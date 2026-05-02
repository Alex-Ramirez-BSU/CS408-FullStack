package com.tss.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 *
 * @SpringBootApplication enables:
 * - Auto-configuration
 * - Component scanning
 * - Configuration support
 */
@SpringBootApplication
public class SurveyApplication {

    /**
     * Starts the Spring Boot application.
     */
    public static void main(String[] args) {
        SpringApplication.run(SurveyApplication.class, args);
    }

}