// 代码生成时间: 2025-08-22 19:31:27
package com.example.xssprotection;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import java.io.IOException;

@Service
public class XssProtectionService {

    /*
     * Sanitizes the input string to prevent XSS attacks.
     * It uses HtmlUtils from Spring framework to escape HTML special characters.
     * @param input The input string that needs to be sanitized.
     * @return The sanitized string.
     */
    public String sanitizeInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        try {
            // Use HtmlUtils to escape HTML special characters
            String sanitizedInput = HtmlUtils.htmlEscape(input);
            return sanitizedInput;
        } catch (IOException e) {
            // Log the exception and rethrow as a runtime exception
            throw new RuntimeException("Error sanitizing input", e);
        }
    }

    /*
     * This method demonstrates how to use the XssProtectionService.
     * It should be called within a controller to sanitize user input.
     * @param userInput The user input that needs to be sanitized.
     * @return The sanitized user input.
     */
    public String handleUserInput(String userInput) {
        // Sanitize the user input to prevent XSS
        String sanitizedInput = sanitizeInput(userInput);
        return sanitizedInput;
    }
}
