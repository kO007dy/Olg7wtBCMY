// 代码生成时间: 2025-09-16 04:41:31
package com.example.security;

import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class PasswordEncryptionDecryptionService {
    // AES key generation
    public static final String ALGORITHM = "AES";
    private static final String KEY_GENERATION_ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;
    
    // Generate a new AES key
    public SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_GENERATION_ALGORITHM);
        keyGenerator.init(KEY_SIZE, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }
    
    // Encrypt the password
    public String encrypt(String password, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    // Decrypt the encrypted password
    public String decrypt(String encryptedPassword, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
    
    // Main method for testing the encryption and decryption
    public static void main(String[] args) {
        try {
            PasswordEncryptionDecryptionService service = new PasswordEncryptionDecryptionService();
            SecretKey key = service.generateKey();
            String password = "mySecretPassword";
            String encryptedPassword = service.encrypt(password, key);
            String decryptedPassword = service.decrypt(encryptedPassword, key);
            
            System.out.println("Original Password: " + password);
            System.out.println("Encrypted Password: " + encryptedPassword);
            System.out.println("Decrypted Password: " + decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}