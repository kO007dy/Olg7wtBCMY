// 代码生成时间: 2025-10-08 23:39:45
package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class MockDataGenerator {

    // Generates a random integer between 1 and 100
    public int generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        return randomNumber;
    }

    // Generates a random string of a specified length
    public String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(characters.charAt(new Random().nextInt(characters.length())));
        }
        return builder.toString();
    }

    // Generates a random email address
    public String generateRandomEmail() {
        String email = generateRandomString(8) + "@example.com";
        return email;
    }

    // Generates a random mock user object
    public User generateMockUser() {
        User user = new User();
        user.setId(generateRandomNumber());
        user.setName(generateRandomString(10));
        user.setEmail(generateRandomEmail());
        return user;
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        MockDataGenerator generator = new MockDataGenerator();
        try {
            User user = generator.generateMockUser();
            System.out.println("Mock User Data: " + user);
        } catch (Exception e) {
            System.out.println("Error generating mock data: " + e.getMessage());
        }
    }
}

/*
 * User.java
 *
 * A simple user class to hold user data.
 */
package com.example.demo;

public class User {
    private int id;
    private String name;
    private String email;

    public User() {}

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
