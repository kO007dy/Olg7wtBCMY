// 代码生成时间: 2025-08-18 06:32:15
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Spring Cloud Application that provides a JSON data format converter service.
 */
@SpringBootApplication
@RestController
public class JsonDataTransformer {

    private final ObjectMapper objectMapper;

    /**
     * Constructor with ObjectMapper to handle JSON processing.
     * @param objectMapper The ObjectMapper instance.
     */
    public JsonDataTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Endpoint to convert JSON data format.
     * @param jsonInput The JSON input string to be converted.
     * @return The converted JSON string in a JSON response.
     */
    @GetMapping("/convert")
    public String convertJson(@RequestParam("json") String jsonInput) {
        try {
            // Parse the input JSON string
            JsonNode jsonNode = objectMapper.readTree(jsonInput);
            // Convert the JSON node back to a JSON string
            String convertedJson = objectMapper.writeValueAsString(jsonNode);
            return convertedJson;
        } catch (JsonProcessingException e) {
            // Handle JSON processing exceptions
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Main method to run the Spring Boot application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(JsonDataTransformer.class, args);
    }
}
