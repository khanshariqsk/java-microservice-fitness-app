package com.fitness.activityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ActivityServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ActivityServiceApp.class, args);
    }
}
