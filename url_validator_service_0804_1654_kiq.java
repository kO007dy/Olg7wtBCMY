// 代码生成时间: 2025-08-04 16:54:53
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Service class to validate the URL link.
 */
@Service
public class UrlValidatorService {

    private final RestTemplate restTemplate;

    /**
     * Constructor for UrlValidatorService.
     * @param restTemplate RestTemplate instance for HTTP requests.
     */
    @Autowired
    public UrlValidatorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Validates the given URL link.
     * @param url The URL to be validated.
     * @return boolean True if URL is valid, false otherwise.
     */
    public boolean validateUrl(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000); // 5 seconds

            int responseCode = connection.getResponseCode();
            // Check if the status code is in the range of successful responses.
            return responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE;
        } catch (Exception e) {
            // Log the exception and return false if the URL is not valid.
            // In a real-world scenario, you would use a logging framework like SLF4J.
            System.err.println("Error validating URL: " + e.getMessage());
            return false;
        }
    }
}
