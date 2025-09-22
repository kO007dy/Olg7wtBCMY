// 代码生成时间: 2025-09-22 15:01:38
package com.example.userpermissionmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserPermissionManagement {

    /**
     * The main method of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(UserPermissionManagement.class, args);
    }

    // Add more components, services, and controllers as needed for the permission management system
    
}

/*
 * Service Layer
 */
package com.example.userpermissionmanagement.service;

import com.example.userpermissionmanagement.model.Permission;
import com.example.userpermissionmanagement.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionService {
    
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * Retrieves all permissions.
     * @return A list of all permissions.
     */
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
    
    /**
     * Retrieves a permission by its ID.
     * @param id The ID of the permission.
     * @return An optional permission.
     */
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }
    
    /**
     * Creates a new permission.
     * @param permission The permission to create.
     * @return The created permission.
     */
    @Transactional
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    /**
     * Updates an existing permission.
     * @param permission The permission to update.
     * @return The updated permission.
     */
    @Transactional
    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    /**
     * Deletes a permission by its ID.
     * @param id The ID of the permission to delete.
     */
    @Transactional
    public void deletePermissionById(Long id) {
        permissionRepository.deleteById(id);
    }
}

/*
 * Repository Layer
 */
package com.example.userpermissionmanagement.repository;

import com.example.userpermissionmanagement.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // Additional repository methods can be defined here
}

/*
 * Model Layer
 */
package com.example.userpermissionmanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission {
    @Id
    private Long id;
    private String name;
    private String description;
    
    // Getters and setters
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

/*
 * Controller Layer
 */
package com.example.userpermissionmanagement.controller;

import com.example.userpermissionmanagement.model.Permission;
import com.example.userpermissionmanagement.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    /**
     * Retrieves all permissions.
     * @return A list of all permissions.
     */
    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }
    
    /**
     * Retrieves a permission by its ID.
     * @param id The ID of the permission.
     * @return A permission if found, otherwise null.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission = permissionService.getPermissionById(id);
        if(permission.isPresent()) {
            return ResponseEntity.ok(permission.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Creates a new permission.
     * @param permission The permission to create.
     * @return The created permission.
     */
    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }
    
    /**
     * Updates an existing permission.
     * @param permission The permission to update.
     * @return The updated permission.
     */
    @PutMapping
    public Permission updatePermission(@RequestBody Permission permission) {
        return permissionService.updatePermission(permission);
    }
    
    /**
     * Deletes a permission by its ID.
     * @param id The ID of the permission to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermissionById(@PathVariable Long id) {
        permissionService.deletePermissionById(id);
        return ResponseEntity.ok().build();
    }
}