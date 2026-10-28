package com.opham.prepa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication

public class PrepaApplication {
//    @Value("${application.security.allowed-origins}")
//    private List<String> allowedOrigins;
    public static void main(String[] args) {
        SpringApplication.run(PrepaApplication.class, args);


    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD")
                        .allowCredentials(true)
                        .allowedHeaders("Authorization", "Cache-Control", "Content-Type")
                        .maxAge(3600);
            }
        };
    }


}
