// 代码生成时间: 2025-09-12 22:31:36
package com.example.urlvalidator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class URLValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(URLValidatorApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            String urlToValidate = "http://example.com"; // Replace with the URL you want to validate
            RestTemplate restTemplate = new RestTemplate();
            try {
                ResponseEntity<String> response = restTemplate.exchange(urlToValidate, HttpMethod.HEAD, null, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    System.out.println("URL is valid: " + urlToValidate);
                } else {
                    System.out.println("URL is invalid or not accessible: " + urlToValidate);
                }
            } catch (Exception e) {
                System.out.println("Error occurred while validating URL: " + e.getMessage());
            }
        };
    }
}
