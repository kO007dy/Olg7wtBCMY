// 代码生成时间: 2025-09-19 20:05:28
 * A simple utility class to calculate hash values for strings.
 */
package com.example.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class HashValueCalculator {
    
    /**
     * Calculates the hash value of a given string using the specified algorithm.
     * 
     * @param inputStr The string to calculate the hash for.
     * @param algorithm The algorithm to use for hashing.
     * @return The hash value as a hexadecimal string.
     * @throws NoSuchAlgorithmException If the algorithm is not available.
     */
    public String calculateHash(String inputStr, String algorithm) throws NoSuchAlgorithmException {
        // Check if the input string is null or empty
        if (inputStr == null || inputStr.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        // Get the MessageDigest instance for the specified algorithm
        MessageDigest md = MessageDigest.getInstance(algorithm);
        
        // Update the MessageDigest with the input string bytes
        md.update(inputStr.getBytes(StandardCharsets.UTF_8));
        
        // Calculate the hash value
        byte[] hashBytes = md.digest();
        
        // Convert the hash bytes to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        
        return hexString.toString();
    }
}
