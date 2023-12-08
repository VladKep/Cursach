package com.example.cursach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CursachApplication {
    public static void main(String[] args) {
        SpringApplication.run(CursachApplication.class, args);
    }
}
