package com.example.jpa_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.jpa_practice")
@EntityScan(basePackages = "com.example.jpa_practice")
@EnableJpaRepositories(basePackages = "com.example.jpa_practice")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
