// 代码生成时间: 2025-09-02 11:20:38
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

/**
 * JsonDataTransformer application.
 */
@SpringBootApplication
public class JsonDataTransformer {

    public static void main(String[] args) {
        SpringApplication.run(JsonDataTransformer.class, args);
    }
}

/**
 * Controller class for JSON data transformation.
 */
@RestController
class JsonDataTransformerController {

    private final ObjectMapper objectMapper;

    /**
     * Constructor with ObjectMapper dependency.
     * @param objectMapper ObjectMapper instance.
     */
    public JsonDataTransformerController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Endpoint to transform JSON data format.
     * @param jsonInput JSON string to be transformed.
     * @return Transformed JSON string.
     */
    @GetMapping("/transform")
    public String transformJson(@RequestParam String jsonInput) {
        try {
            // Parse the input JSON string to a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonInput);
            // Convert the JsonNode back to a JSON string
            return objectMapper.writeValueAsString(jsonNode);
        } catch (IOException e) {
            // Handle JSON parsing error
            return "Error: Invalid JSON input.";
        }
    }
}
