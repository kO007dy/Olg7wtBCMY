// 代码生成时间: 2025-10-04 21:31:42
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudDataModelApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDataModelApp.class, args);
    }
}

// 数据模型
package com.example.demo.model;

public class DataModel {
    private Long id;
    private String name;
    private Integer age;

    // 构造器
    public DataModel() {}

    // Getter 和 Setter
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
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}

// REST Controller
package com.example.demo.controller;

import com.example.demo.model.DataModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataModelController {

    // 模拟数据库操作
    private final DataModelRepository repository;

    public DataModelController(DataModelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dataModels/{id}")
    public DataModel getDataModelById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("DataModel not found"));
    }
}

// 数据模型仓库接口
package com.example.demo.repository;

import com.example.demo.model.DataModel;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface DataModelRepository extends CrudRepository<DataModel, Long> {
    Optional<DataModel> findById(Long id);
}
