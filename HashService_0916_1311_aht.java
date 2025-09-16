// 代码生成时间: 2025-09-16 13:11:53
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

/**
 * 哈希值计算工具服务
 */
@Service
public class HashService {

    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final String HASH_ALGORITHM_SHA256 = "SHA-256";

    /**
     * 计算MD5哈希值
     *
     * @param input 输入字符串
     * @return MD5哈希值
     * @throws NoSuchAlgorithmException 如果MD5算法不可用
     */
    public String calculateMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM_MD5);
        byte[] hash = digest.digest(input.getBytes());
        BigInteger no = new BigInteger(1, hash);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    /**
     * 计算SHA-256哈希值
     *
     * @param input 输入字符串
     * @return SHA-256哈希值
     * @throws NoSuchAlgorithmException 如果SHA-256算法不可用
     */
    public String calculateSHA256Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM_SHA256);
        byte[] hash = digest.digest(input.getBytes());
        BigInteger no = new BigInteger(1, hash);
        String hashtext = no.toString(16);
        while (hashtext.length() < 64) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
