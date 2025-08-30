// 代码生成时间: 2025-08-30 15:02:34
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SystemPerformanceMonitor {

    public static void main(String[] args) {
        SpringApplication.run(SystemPerformanceMonitor.class, args);
    }

    @GetMapping("/performance")
    public Map<String, Object> getSystemPerformance() {
        Map<String, Object> performanceInfo = new HashMap<>();
        Runtime runtime = Runtime.getRuntime();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = allocatedMemory - freeMemory;

        long uptime = runtimeMXBean.getUptime();

        performanceInfo.put("maxMemory", maxMemory / (1024 * 1024) + " MB"); // Max memory in MB
        performanceInfo.put("allocatedMemory", allocatedMemory / (1024 * 1024) + " MB"); // Allocated memory in MB
        performanceInfo.put("freeMemory", freeMemory / (1024 * 1024) + " MB"); // Free memory in MB
        performanceInfo.put("usedMemory", usedMemory / (1024 * 1024) + " MB"); // Used memory in MB
        performanceInfo.put("uptime", uptime + " ms"); // Uptime in milliseconds

        return performanceInfo;
    }
}
