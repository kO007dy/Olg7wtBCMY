// 代码生成时间: 2025-09-08 17:27:43
package com.example.preprocessing;

import org.springframework.stereotype.Component;

@Component
public class DataPreprocessingTool {

    // Trims and converts the input string to lowercase
    public String cleanString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        return input.trim().toLowerCase();
    }

    // Removes special characters from the input string
    public String removeSpecialCharacters(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        // Replace all special characters with an empty string
        String cleanedInput = input.replaceAll("[^a-zA-Z0-9_\-]","");
        return cleanedInput;
    }

    // Example usage of the data preprocessing tool
    public static void main(String[] args) {
        try {
            DataPreprocessingTool tool = new DataPreprocessingTool();
            
            String rawData = "  Example: Data with SPECIAL CHARACTERS!!!  ";
            String cleanedData = tool.cleanString(rawData);
            System.out.println("Cleaned string: " + cleanedData);
            String processedData = tool.removeSpecialCharacters(cleanedData);
            System.out.println("Processed string: " + processedData);
        } catch (IllegalArgumentException e) {
            System.err.println("Error processing data: " + e.getMessage());
        }
    }
}
