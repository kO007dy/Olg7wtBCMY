// 代码生成时间: 2025-08-24 11:44:24
package com.example.urlvalidator;

import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class UrlValidatorService {

    /**
     * Validates the given URL.
     *
     * @param urlString The URL to validate.
     * @return true if the URL is valid, false otherwise.
     */
    public boolean validateUrl(String urlString) {
        try {
            // Attempt to create a URL object from the string.
            // This will throw a MalformedURLException if the URL is invalid.
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            // Log the exception for debugging purposes.
            // In a real-world application, you would use a logging framework.
            System.err.println("Invalid URL: " + urlString);
            return false;
        }
    }
}
