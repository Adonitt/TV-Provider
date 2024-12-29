package org.example.finalproject.admin.security;

import org.example.finalproject.admin.repositories.DataInitializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ApplicationRunner applicationRunner(DataInitializer di) {
        return args -> di.run();
    }
}