// 代码生成时间: 2025-08-18 19:58:22
 * IntegrationTestService.java
 *
 * This service class provides integration testing functionality.
 * It encapsulates the testing logic for the microservices.
 */

package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import java.util.HashMap;
import java.util.Map;

@Service
public class IntegrationTestService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Performs an integration test by sending a request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param method The HTTP method to use for the request.
     * @param headers The headers to include in the request.
     * @param body The body of the request.
     * @return The response entity from the server.
     */
    public ResponseEntity<String> performIntegrationTest(String url, HttpMethod method, Map<String, String> headers, String body) {
        try {
            // Create a map to store the headers.
            Map<String, String> headerMap = new HashMap<>();
            headers.forEach(headerMap::put);

            // Perform the request based on the specified HTTP method.
            switch (method) {
                case GET:
                    return restTemplate.getForEntity(url, String.class, headerMap);
                case POST:
                    return restTemplate.postForEntity(url, body, String.class, headerMap);
                case PUT:
                    return restTemplate.exchange(url, method,
                            restTemplate.getRequestFactory().createRequestEntity(body, headerMap, HttpMethod.PUT),
                            String.class);
                case DELETE:
                    return restTemplate.exchange(url, method,
                            restTemplate.getRequestFactory().createRequestEntity(null, headerMap, HttpMethod.DELETE),
                            String.class);
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }
        } catch (Exception e) {
            // Handle exceptions and provide meaningful error messages.
            return ResponseEntity.status(ResponseEntity.statusForCode(500)).body("An error occurred during integration testing: " + e.getMessage());
        }
    }
}
