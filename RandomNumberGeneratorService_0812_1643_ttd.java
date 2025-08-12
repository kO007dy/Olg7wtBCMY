// 代码生成时间: 2025-08-12 16:43:42
package com.example.randomnumbergenerator;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class RandomNumberGeneratorService {

    private final Random random = new Random();

    /**
     * Generates a random integer between the specified minimum and maximum values.
     *
     * @param min The minimum value for the random number.
     * @param max The maximum value for the random number.
     * @return A random integer between min and max.
     * @throws IllegalArgumentException If min is greater than max.
     */
    public int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.");
        }
        return random.nextInt(max - min + 1) + min;
    }
}
