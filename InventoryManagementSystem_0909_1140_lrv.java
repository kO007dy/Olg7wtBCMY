// 代码生成时间: 2025-09-09 11:40:50
package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryManagementSystem {
    
    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }
}

/*
 * InventoryService.java
 * This service class provides business logic for inventory operations.
 */
package com.example.inventory.service;

import org.springframework.stereotype.Service;
import com.example.inventory.exception.InventoryException;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private static final int MIN_STOCK = 0;
    
    public InventoryService(InventoryRepository repository) {
        this.inventoryRepository = repository;
    }
    
    // Add Inventory Item
    public void addItem(String item, int quantity) throws InventoryException {
        if(quantity < 0) {
            throw new InventoryException("Quantity cannot be negative");
        }
        inventoryRepository.save(new InventoryItem(item, quantity));
    }
    
    // Remove Inventory Item
    public void removeItem(String item, int quantity) throws InventoryException {
        InventoryItem existingItem = inventoryRepository.findByItem(item);
        if(existingItem == null) {
            throw new InventoryException("Item not found in inventory");
        }
        if(quantity > existingItem.getQuantity()) {
            throw new InventoryException("Quantity exceeds available stock");
        }
        existingItem.setQuantity(existingItem.getQuantity() - quantity);
        inventoryRepository.save(existingItem);
    }
    
    // Get Inventory Item
    public InventoryItem getItem(String item) {
        return inventoryRepository.findByItem(item);
    }
}

/*
 * InventoryRepository.java
 * This repository interface provides methods for database operations.
 */
package com.example.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.inventory.model.InventoryItem;

public interface InventoryRepository extends CrudRepository<InventoryItem, Long> {
    InventoryItem findByItem(String item);
}

/*
 * InventoryItem.java
 * This entity class represents an inventory item.
 */
package com.example.inventory.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InventoryItem {
    @Id
    private Long id;
    private String item;
    private int quantity;
    
    public InventoryItem() {
    }
    
    public InventoryItem(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    
    // Getters and setters
    // ...
}

/*
 * InventoryException.java
 * Custom exception class for inventory related errors.
 */
package com.example.inventory.exception;

public class InventoryException extends Exception {
    public InventoryException(String message) {
        super(message);
    }
}
