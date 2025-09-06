// 代码生成时间: 2025-09-07 04:55:46
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class TestDataGenerator {

    private Random random = new Random();

    /**
     * Generates a random integer between the given range.
     *
     * @param min The minimum value of the range (inclusive).
     * @param max The maximum value of the range (exclusive).
     * @return A random integer within the specified range.
     */
    public int generateRandomInt(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        return random.nextInt(max - min) + min;
    }

    /**
     * Generates a random string of specified length.
     *
     * @param length The length of the string to be generated.
     * @return A random string of the specified length.
     */
    public String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            result[i] = characters.charAt(random.nextInt(characters.length()));
        }

        return new String(result);
    }

    /**
     * Generates a random email address.
     *
     * @return A randomly generated email address.
     */
    public String generateRandomEmail() {
        return generateRandomString(10) + "@example.com";
    }

    /**
     * Main method for testing purposes.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        TestDataGenerator generator = new TestDataGenerator();

        try {
            int testInt = generator.generateRandomInt(1, 100);
            String testString = generator.generateRandomString(15);
            String testEmail = generator.generateRandomEmail();

            System.out.println("Random Integer: " + testInt);
            System.out.println("Random String: " + testString);
            System.out.println("Random Email: " + testEmail);

        } catch (Exception e) {
            System.err.println("Error generating test data: " + e.getMessage());
        }
    }
}
