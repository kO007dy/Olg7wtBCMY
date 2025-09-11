// 代码生成时间: 2025-09-11 22:00:12
package com.example.crypto;

import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class PasswordCryptoService {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";

    private SecretKey secretKey;

    public PasswordCryptoService() {
        try {
            // Generate a secret key for AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(128, SecureRandom.getInstance(RANDOM_ALGORITHM));
            this.secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing the crypto service", e);
        }
    }

    /**
     * Encrypts the given password using AES encryption.
     * 
     * @param password The password to encrypt.
     * @return The encrypted password.
     */
    public String encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting the password", e);
        }
    }

    /**
     * Decrypts the given encrypted password using AES decryption.
     * 
     * @param encryptedPassword The encrypted password to decrypt.
     * @return The decrypted password.
     */
    public String decrypt(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting the password", e);
        }
    }

    // Getter for the secret key (for testing purposes)
    public SecretKey getSecretKey() {
        return secretKey;
    }
}
