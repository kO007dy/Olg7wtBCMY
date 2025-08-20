// 代码生成时间: 2025-08-20 15:07:14
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Service class for generating random numbers.
 */
@Service
public class RandomNumberGeneratorService {

    private final Random random;

    /**
     * Constructor that initializes the Random object.
     */
    public RandomNumberGeneratorService() {
        this.random = new Random();
    }

    /**
     * Generates a random number within a specified range.
     * 
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return a random number between min and max
     * @throws IllegalArgumentException if min is greater than max
     */
    public int generateRandomNumber(int min, int max) {
        // Check if the range is valid
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }

        // Generate and return a random number within the range
        return random.nextInt(max - min) + min;
    }
}
