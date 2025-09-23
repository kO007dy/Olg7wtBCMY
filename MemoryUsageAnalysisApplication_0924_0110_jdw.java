// 代码生成时间: 2025-09-24 01:10:43
 * It exposes an endpoint to retrieve memory metrics.
 */

package com.example.memoryanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;

@SpringBootApplication
public class MemoryUsageAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryUsageAnalysisApplication.class, args);
    }
}

/**
 * MemoryUsageController.java
 *
 * Provides an API endpoint to get memory usage metrics.
 */
@RestController
class MemoryUsageController {

    private final MemoryMXBean memoryMXBean;
    private final MemoryPoolMXBean heapMemoryPoolMXBean;

    public MemoryUsageController() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
        this.heapMemoryPoolMXBean = ManagementFactory.getMemoryPoolMXBeans()
                .stream()
                .filter(MemoryPoolMXBean::isUsageThresholdSupported)
                .filter(pool -> pool.getType() == MemoryType.HEAP)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Heap memory pool not found"));
    }

    /**
     * GET endpoint to retrieve memory metrics.
     *
     * @return Memory metrics in JSON format.
     */
    @GetMapping("/memory")
    public MemoryMetrics getMemoryMetrics() {
        return new MemoryMetrics(
                memoryMXBean.getHeapMemoryUsage(),
                heapMemoryPoolMXBean.getUsage(),
                heapMemoryPoolMXBean.getPeakUsage(),
                heapMemoryPoolMXBean.getCollectionUsage()
        );
    }
}

/**
 * MemoryMetrics.java
 *
 * Data transfer object for memory usage metrics.
 */
class MemoryMetrics {

    private final long heapUsed;
    private final long heapMax;
    private final long heapCommitted;
    private final long heapUsedAfterGc;
    private final long heapInit;
    private final long nonHeapUsed;
    private final long nonHeapMax;
    private final long nonHeapCommitted;
    private final long nonHeapInit;

    public MemoryMetrics(
            long heapUsed,
            long heapMax,
            long heapCommitted,
            long heapUsedAfterGc,
            long heapInit,
            long nonHeapUsed,
            long nonHeapMax,
            long nonHeapCommitted,
            long nonHeapInit) {
        this.heapUsed = heapUsed;
        this.heapMax = heapMax;
        this.heapCommitted = heapCommitted;
        this.heapUsedAfterGc = heapUsedAfterGc;
        this.heapInit = heapInit;
        this.nonHeapUsed = nonHeapUsed;
        this.nonHeapMax = nonHeapMax;
        this.nonHeapCommitted = nonHeapCommitted;
        this.nonHeapInit = nonHeapInit;
    }

    public long getHeapUsed() {
        return heapUsed;
    }

    public long getHeapMax() {
        return heapMax;
    }

    public long getHeapCommitted() {
        return heapCommitted;
    }

    public long getHeapUsedAfterGc() {
        return heapUsedAfterGc;
    }

    public long getHeapInit() {
        return heapInit;
    }

    public long getNonHeapUsed() {
        return nonHeapUsed;
    }

    public long getNonHeapMax() {
        return nonHeapMax;
    }

    public long getNonHeapCommitted() {
        return nonHeapCommitted;
    }

    public long getNonHeapInit() {
        return nonHeapInit;
    }
}
