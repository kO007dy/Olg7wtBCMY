// 代码生成时间: 2025-08-26 01:12:14
package com.example.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * A utility class to analyze memory usage.
 */
public class MemoryUsageAnalyzer {

    private final MemoryMXBean memoryMXBean;

    /**
     * Constructor initializes the MemoryMXBean.
     */
    public MemoryUsageAnalyzer() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }

    /**
     * Retrieves and prints the current memory usage.
     */
    public void printMemoryUsage() {
        try {
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

            printMemoryUsageDetails(heapMemoryUsage, "Heap Memory");
            printMemoryUsageDetails(nonHeapMemoryUsage, "Non-Heap Memory");

        } catch (Exception e) {
            // Handle the exception if there's an error getting memory usage
            System.err.println("Error while retrieving memory usage: " + e.getMessage());
        }
    }

    /**
     * Prints the details of the memory usage.
     *
     * @param memoryUsage The memory usage details to print.
     * @param memoryType  The type of memory (Heap or Non-Heap).
     */
    private void printMemoryUsageDetails(MemoryUsage memoryUsage, String memoryType) {
        String info = String.format("%s Memory Usage:
" +
                "  - Init: %d bytes
" +
                "  - Used: %d bytes
" +
                "  - Committed: %d bytes
" +
                "  - Max: %d bytes
" +
                "  - Threshold: %d bytes
",
                memoryType,
                memoryUsage.getInit(),
                memoryUsage.getUsed(),
                memoryUsage.getCommitted(),
                memoryUsage.getMax(),
                memoryUsage.getThreshold());

        System.out.println(info);
    }

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        MemoryUsageAnalyzer analyzer = new MemoryUsageAnalyzer();
        analyzer.printMemoryUsage();
    }
}
