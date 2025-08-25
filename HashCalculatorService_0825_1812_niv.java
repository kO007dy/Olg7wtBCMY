// 代码生成时间: 2025-08-25 18:12:45
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * HashCalculatorService provides functionality to calculate hash values of given data.
 */
@Service
public class HashCalculatorService {
# FIXME: 处理边界情况

    /**
     * Calculates the hash value of the provided string using SHA-256 algorithm.
     *
     * @param input The input string to be hashed.
# 添加错误处理
     * @return The SHA-256 hash of the input string as a hex string.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public String calculateSha256Hash(String input) throws NoSuchAlgorithmException {
        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
# 优化算法效率
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();

        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
