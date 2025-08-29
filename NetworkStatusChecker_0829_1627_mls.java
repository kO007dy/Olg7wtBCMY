// 代码生成时间: 2025-08-29 16:27:22
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Spring Cloud Application for Network Status Check
 */
@SpringBootApplication
@RestController
public class NetworkStatusChecker {

    private static final String HOST_TO_PING = "8.8.8.8"; // Google's DNS server for testing
    private static final String UNKNOWN_HOST_ERROR = "Unknown host exception caught";

    @GetMapping("/check")
    public String checkNetworkStatus() {
        try {
            InetAddress.getByName(HOST_TO_PING);
            return "Network is up and reachable.";
        } catch (UnknownHostException e) {
            return "Network is down or unreachable.";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NetworkStatusChecker.class, args);
    }
}
