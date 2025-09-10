// 代码生成时间: 2025-09-11 04:10:17
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class TestDataGenerator {

    private static final Random random = new Random();

    // Generates a random string of specified length
    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    // Generates a random integer within a specified range
    private int generateRandomInteger(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    // Generates a random boolean value
    private boolean generateRandomBoolean() {
        return random.nextBoolean();
    }

    // Generates a simple test data object
    public TestData generateTestData() {
        TestData testData = new TestData();
        try {
            testData.setId(generateRandomInteger(1, 100));
            testData.setName(generateRandomString(10));
            testData.setActive(generateRandomBoolean());
        } catch (Exception e) {
            // Handle any unexpected errors
            System.err.println("Error generating test data: " + e.getMessage());
        }
        return testData;
    }
}

/**
 * TestData.java
 * <p>
 * A simple data class representing the test data to be generated.
 * </p>
 */

public class TestData {

    private int id;
    private String name;
    private boolean active;

    // Standard getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TestData{"id": " + id + ", "name": "" + name + "", "active": " + active + "}";
    }
}