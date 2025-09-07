// 代码生成时间: 2025-09-08 00:51:56
import org.springframework.stereotype.Component;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class NetworkStatusChecker {

    private static final String DEFAULT_HOST = "8.8.8.8"; // Google's public DNS server

    /**
     * Checks if the network connection is available by pinging a default host.
     * 
     * @return A boolean indicating whether the network connection is available.
     */
    public boolean checkNetworkConnection() {
        return checkNetworkConnection(DEFAULT_HOST);
    }

    /**
     * Checks if the network connection is available by pinging a specified host.
     * 
     * @param host The host to ping for checking network connection.
     * @return A boolean indicating whether the network connection is available.
     */
    public boolean checkNetworkConnection(String host) {
        try {
            InetAddress ipAddr = InetAddress.getByName(host);
            return ipAddr.isReachable(5000); // Timeout of 5000 milliseconds
        } catch (UnknownHostException e) {
            System.err.println("Host " + host + " could not be resolved.");
        } catch (Exception e) {
            System.err.println("An error occurred while checking network connection: " + e.getMessage());
        }
        return false;
    }
}
