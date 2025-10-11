// 代码生成时间: 2025-10-11 19:20:36
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class BlockchainBrowserApplication {

    private final RestTemplate restTemplate;

    public BlockchainBrowserApplication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/blockchain")
    public String getBlockchainInfo() {
        try {
            // 假设区块链网络提供了一个API端点来获取区块链信息
            String blockchainInfo = restTemplate.getForObject(
                "http://blockchain-network-api/blockchain",
                String.class
            );
            return blockchainInfo;
        } catch (Exception e) {
            // 错误处理
            return "Error retrieving blockchain information: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BlockchainBrowserApplication.class, args);
    }
}