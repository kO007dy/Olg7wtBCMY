// 代码生成时间: 2025-08-08 00:39:27
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ConcurrentHashMap;

// InventoryItem class represents an item in the inventory
class InventoryItem {
    private String id;
    private String name;
    private int quantity;

    public InventoryItem(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

// InventoryService class handles the business logic for inventory management
class InventoryService {
    private ConcurrentHashMap<String, InventoryItem> inventory = new ConcurrentHashMap<>();

    public InventoryItem addItem(String id, String name, int quantity) {
        if (inventory.containsKey(id)) {
            throw new IllegalArgumentException("Item with id already exists");
        }
        inventory.put(id, new InventoryItem(id, name, quantity));
        return inventory.get(id);
    }

    public InventoryItem updateItem(String id, String name, int quantity) {
        if (!inventory.containsKey(id)) {
            throw new IllegalArgumentException("Item with id does not exist");
        }
        InventoryItem item = inventory.get(id);
        item.setName(name);
        item.setQuantity(quantity);
        return item;
    }

    public InventoryItem getItem(String id) {
        return inventory.get(id);
    }

    public void removeItem(String id) {
        if (!inventory.containsKey(id)) {
            throw new IllegalArgumentException("Item with id does not exist");
        }
        inventory.remove(id);
    }
}

// InventoryController class handles HTTP requests related to inventory management
@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class InventoryManagementSystem {

    private InventoryService inventoryService = new InventoryService();

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementSystem.class, args);
    }

    // Endpoint to add a new item to the inventory
    @PostMapping("/inventory")
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return inventoryService.addItem(item.getId(), item.getName(), item.getQuantity());
    }

    // Endpoint to update an existing item in the inventory
    @PostMapping("/inventory/{id}")
    public InventoryItem updateItem(@PathVariable String id, @RequestBody InventoryItem newItem) {
        return inventoryService.updateItem(id, newItem.getName(), newItem.getQuantity());
    }

    // Endpoint to retrieve an item from the inventory
    @GetMapping("/inventory/{id}")
    public InventoryItem getItem(@PathVariable String id) {
        return inventoryService.getItem(id);
    }

    // Endpoint to remove an item from the inventory
    @PostMapping("/inventory/remove/{id}")
    public void removeItem(@PathVariable String id) {
        inventoryService.removeItem(id);
    }
}
