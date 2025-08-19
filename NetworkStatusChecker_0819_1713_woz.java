// 代码生成时间: 2025-08-19 17:13:50
package com.example.networkstatuschecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
@RestController
public class NetworkStatusChecker {
    @GetMapping("/check")
    public String checkNetworkStatus(String url) {
        try {
            URL website = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "Network connection is active and the URL is reachable.";
            } else {
                return "Network connection is not active or the URL is unreachable: HTTP Status Code - " + responseCode;
            }
        } catch (Exception e) {
            // Log the exception, e.g., using SLF4J Logger
            // logger.error("Error checking network status", e);
            return "An error occurred while checking the network status: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NetworkStatusChecker.class, args);
    }
}