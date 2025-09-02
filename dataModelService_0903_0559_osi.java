// 代码生成时间: 2025-09-03 05:59:57
package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DataModel represents the basic structure of the data model entity.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}

package com.example.demo.repository;

import com.example.demo.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * DataModelRepository provides the data access layer for the DataModel entity.
 */
@Repository
public interface DataModelRepository extends JpaRepository<DataModel, Long> {
    List<DataModel> findByName(String name);
}

package com.example.demo.service;

import com.example.demo.model.DataModel;
import com.example.demo.repository.DataModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * DataModelService is the service layer for managing DataModel entities.
 * It provides the business logic and error handling for the data model operations.
 */
@Service
public class DataModelService {
    @Autowired
    private DataModelRepository dataModelRepository;

    /**
     * Retrieves a DataModel by its ID.
     * @param id The ID of the DataModel to retrieve.
     * @return An Optional DataModel instance.
     */
    public Optional<DataModel> getDataModelById(Long id) {
        return dataModelRepository.findById(id);
    }

    /**
     * Saves a DataModel to the database.
     * @param dataModel The DataModel to save.
     * @return The saved DataModel instance.
     */
    public DataModel saveDataModel(DataModel dataModel) {
        return dataModelRepository.save(dataModel);
    }

    /**
     * Retrieves a list of DataModel instances by their name.
     * @param name The name to filter by.
     * @return A list of DataModel instances.
     */
    public List<DataModel> getDataModelByName(String name) {
        return dataModelRepository.findByName(name);
    }

    /**
     * Deletes a DataModel by its ID.
     * @param id The ID of the DataModel to delete.
     */
    public void deleteDataModelById(Long id) {
        dataModelRepository.deleteById(id);
    }

    /**
     * Retrieves all DataModel instances.
     * @return A list of all DataModel instances.
     */
    public List<DataModel> getAllDataModels() {
        return dataModelRepository.findAll();
    }
}

package com.example.demo.controller;

import com.example.demo.model.DataModel;
import com.example.demo.service.DataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * DataModelController provides RESTful endpoints for DataModel entities.
 */
@RestController
@RequestMapping("/api/datamodels")
public class DataModelController {
    @Autowired
    private DataModelService dataModelService;

    /**
     * GET endpoint to retrieve all DataModel instances.
     * @return A list of all DataModel instances.
     */
    @GetMapping
    public List<DataModel> getAllDataModels() {
        return dataModelService.getAllDataModels();
    }

    /**
     * GET endpoint to retrieve a DataModel by its ID.
     * @param id The ID of the DataModel to retrieve.
     * @return A ResponseEntity wrapping the DataModel instance.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DataModel> getDataModelById(@PathVariable Long id) {
        return dataModelService.getDataModelById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST endpoint to create a new DataModel instance.
     * @param dataModel The DataModel instance to create.
     * @return A ResponseEntity wrapping the created DataModel instance.
     */
    @PostMapping
    public ResponseEntity<DataModel> createDataModel(@RequestBody DataModel dataModel) {
        DataModel createdModel = dataModelService.saveDataModel(dataModel);
        return ResponseEntity.ok(createdModel);
    }

    /**
     * PUT endpoint to update an existing DataModel instance.
     * @param id The ID of the DataModel to update.
     * @param dataModel The updated DataModel instance.
     * @return A ResponseEntity wrapping the updated DataModel instance.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DataModel> updateDataModel(@PathVariable Long id, @RequestBody DataModel dataModel) {
        if (!dataModelService.getDataModelById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        dataModel.setId(id);
        return ResponseEntity.ok(dataModelService.saveDataModel(dataModel));
    }

    /**
     * DELETE endpoint to delete a DataModel by its ID.
     * @param id The ID of the DataModel to delete.
     * @return A ResponseEntity indicating the result of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataModel(@PathVariable Long id) {
        if (!dataModelService.getDataModelById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        dataModelService.deleteDataModelById(id);
        return ResponseEntity.ok().build();
    }
}