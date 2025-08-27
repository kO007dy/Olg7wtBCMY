// 代码生成时间: 2025-08-27 18:55:50
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * HashCalculatorService provides functionality to calculate hash values for input strings.
 */
@Service
public class HashCalculatorService {

    /**
     * Calculates the SHA-256 hash value of the provided input string.
     * 
     * @param input The string to be hashed.
     * @return The SHA-256 hash value as a hexadecimal string.
     * @throws RuntimeException if a NoSuchAlgorithmException occurs.
     */
    public String calculateSHA256Hash(String input) {
        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        try {
            // Get an instance of the SHA-256 hash function
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hashing operation
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array into a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Log the exception and rethrow as a RuntimeException
            // Log the exception (logging framework not specified, so commented out)
            // logger.error("Failed to calculate hash", e);
            throw new RuntimeException("Failed to calculate hash", e);
        }
    }
}