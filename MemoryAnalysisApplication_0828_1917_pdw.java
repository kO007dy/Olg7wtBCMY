// 代码生成时间: 2025-08-28 19:17:36
package com.example.memoryanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@SpringBootApplication
@RestController
public class MemoryAnalysisApplication {

    private static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) {
        SpringApplication.run(MemoryAnalysisApplication.class, args);
    }

    /**
     * Endpoint to fetch and analyze memory usage.
     * 
     * @return A JSON object representing the memory usage analysis.
     */
    @GetMapping("/memory")
    public String analyzeMemory() {
        try {
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
            
            long heapUsed = heapMemoryUsage.getUsed();
            long heapMax = heapMemoryUsage.getMax();
            long nonHeapUsed = nonHeapMemoryUsage.getUsed();
            long nonHeapMax = nonHeapMemoryUsage.getMax();
            
            return String.format("{"heapUsed":%d, "heapMax":%d, "nonHeapUsed":%d, "nonHeapMax":%d}",
                              heapUsed, heapMax, nonHeapUsed, nonHeapMax);
        } catch (Exception e) {
            // Log the exception and return an error message
            // In a real-world application, you'd use a logging framework instead of System.out.println
            System.out.println("Error analyzing memory: " + e.getMessage());
            return "{"error":"Memory analysis failed"}";
        }
    }
}
