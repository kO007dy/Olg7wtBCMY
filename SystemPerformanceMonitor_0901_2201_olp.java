// 代码生成时间: 2025-09-01 22:01:21
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.*;

@Service
public class SystemPerformanceMonitor {

    private final OperatingSystemMXBean osBean;
    private final RuntimeMXBean runtimeBean;
    private final ThreadMXBean threadBean;

    @Autowired
    public SystemPerformanceMonitor(OperatingSystemMXBean osBean, RuntimeMXBean runtimeBean, ThreadMXBean threadBean) {
        this.osBean = osBean;
        this.runtimeBean = runtimeBean;
        this.threadBean = threadBean;
    }

    /**
     * Retrieves the current system performance metrics.
     *
     * @return A map containing system performance metrics.
     */
    public Map<String, Object> getSystemPerformanceMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        // CPU usage
        double cpuLoad = osBean.getSystemCpuLoad();
        metrics.put("CPU Load", cpuLoad);

        // System load average
        double systemLoadAverage = osBean.getSystemLoadAverage();
        metrics.put("System Load Average", systemLoadAverage);

        // Free physical memory size
        long freePhysicalMemorySize = osBean.getFreePhysicalMemorySize();
        metrics.put("Free Physical Memory Size", freePhysicalMemorySize);

        // Total physical memory size
        long totalPhysicalMemorySize = osBean.getTotalPhysicalMemorySize();
        metrics.put("Total Physical Memory Size", totalPhysicalMemorySize);

        // Used memory
        long usedMemory = totalPhysicalMemorySize - freePhysicalMemorySize;
        metrics.put("Used Memory", usedMemory);

        // Uptime
        long uptime = runtimeBean.getUptime();
        metrics.put("Uptime", uptime);

        // Number of threads
        long threadCount = threadBean.getThreadCount();
        metrics.put("Thread Count", threadCount);

        return metrics;
    }

    /**
     * Handles any exceptions that occur during system performance monitoring.
     *
     * @param e The exception that occurred.
     */
    private void handleException(Exception e) {
        // Log the exception or handle it as per the application's error handling policy
        System.err.println("An error occurred while monitoring system performance: " + e.getMessage());
    }

    public static void main(String[] args) {
        SystemPerformanceMonitor monitor = new SystemPerformanceMonitor(ManagementFactory.getOperatingSystemMXBean(),
                                                                      ManagementFactory.getRuntimeMXBean(),
                                                                      ManagementFactory.getThreadMXBean());

        try {
            Map<String, Object> metrics = monitor.getSystemPerformanceMetrics();
            System.out.println("System Performance Metrics: ");
            metrics.forEach((key, value) -> System.out.println(key + ": " + value));
        } catch (Exception e) {
            monitor.handleException(e);
        }
    }
}