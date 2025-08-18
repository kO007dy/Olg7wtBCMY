// 代码生成时间: 2025-08-18 15:27:46
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryManagementSystem {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }
}

@RestController
class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory")
    public InventoryItem addInventoryItem(@RequestBody InventoryItem item) {
        return inventoryService.addInventoryItem(item);
    }

    @GetMapping("/inventory/{id}")
    public InventoryItem getInventoryItemById(@PathVariable("id") Long id) {
        return inventoryService.getInventoryItemById(id);
    }
}

class InventoryService {
    private final ConcurrentHashMap<Long, InventoryItem> inventoryItems = new ConcurrentHashMap<>();

    public InventoryItem addInventoryItem(InventoryItem item) {
        if (item == null || item.getId() == null) {
            throw new IllegalArgumentException("Inventory item ID cannot be null");
        }
        inventoryItems.put(item.getId(), item);
        return item;
    }

    public InventoryItem getInventoryItemById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Inventory item ID cannot be null");
        }
        return inventoryItems.get(id);
    }
}

class InventoryItem {
    private Long id;
    private String name;
    private int quantity;

    public InventoryItem() {
    }

    public InventoryItem(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
