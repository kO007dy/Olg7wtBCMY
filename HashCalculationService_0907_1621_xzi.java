// 代码生成时间: 2025-09-07 16:21:34
package com.example.hashservice;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@Service
public class HashCalculationService {

    /**
     * Calculates the hash value for a given string using SHA-256 algorithm.
     * 
     * @param input The string to be hashed.
     * @return The hash value as a hexadecimal string.
     * @throws NoSuchAlgorithmException If SHA-256 algorithm is not available.
     */
    public String calculateHash(String input) throws NoSuchAlgorithmException {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }
        
        // Get the SHA-256 MessageDigest instance
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        
        // Update the digest using the input string bytes
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        
        // Compute the hash value
        byte[] hashBytes = digest.digest();
        
        // Convert the byte array into a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
}
