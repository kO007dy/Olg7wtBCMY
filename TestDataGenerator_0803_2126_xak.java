// 代码生成时间: 2025-08-03 21:26:44
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@RestController
public class TestDataGenerator {

    private static final Random random = new Random();

    /**
     * Generates a random UUID and returns it as a string.
     *
     * @return A randomly generated UUID string.
     */
    @GetMapping("/generate")
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a random integer between 0 and 100.
     *
     * @return A random integer between 0 and 100.
     */
    @GetMapping("/generate/number")
    public int generateRandomNumber() {
        return random.nextInt(101);
    }

    /**
     * Generates a random string of a given length.
     *
     * @param length The length of the string to generate.
     * @return A randomly created string of the given length.
     */
    @GetMapping("/generate/string")
    public String generateRandomString(@RequestParam("length") int length) {
        if (length <= 0) {
            // Handle invalid length
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('A' + random.nextInt(26)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestDataGenerator.class, args);
    }
}
