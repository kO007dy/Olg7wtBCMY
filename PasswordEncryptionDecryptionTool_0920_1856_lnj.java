// 代码生成时间: 2025-09-20 18:56:52
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * A utility class for password encryption and decryption using AES algorithm.
 */
@Component
public class PasswordEncryptionDecryptionTool {

    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_CIPHER = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 128;

    /**
     * Encrypts a password using AES algorithm.
     * 
     * @param password The password to be encrypted.
     * @return The encrypted password as a base64 encoded string.
     * @throws Exception If encryption fails.
     */
    public String encryptPassword(String password) throws Exception {
        SecretKey key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM_CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts a password using AES algorithm.
     * 
     * @param encryptedPassword The encrypted password to be decrypted.
     * @return The decrypted password.
     * @throws Exception If decryption fails.
     */
    public String decryptPassword(String encryptedPassword) throws Exception {
        SecretKey key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM_CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    /**
     * Generates a secret key for AES encryption and decryption.
     * 
     * @return The generated secret key.
     * @throws Exception If key generation fails.
     */
    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE, new SecureRandom());
        return keyGenerator.generateKey();
    }
}
