// 代码生成时间: 2025-08-14 00:19:43
package com.example.demo.model;

/**
 * 定义数据模型服务
 */
public interface DataModelService {
    
    /**
     * 获取数据模型的详细信息
     *
     * @param modelId 模型ID
     * @return 数据模型对象
     */
    DataModel getDataModelById(String modelId);
    
    /**
     * 保存或更新数据模型
     *
     * @param model 数据模型对象
     * @return 保存或更新后的数据模型对象
     */
    DataModel saveOrUpdateDataModel(DataModel model);
    
    /**
     * 删除数据模型
     *
     * @param modelId 模型ID
     * @throws DataModelNotFoundException 当模型不存在时抛出异常
     */
    void deleteDataModelById(String modelId) throws DataModelNotFoundException;
}

package com.example.demo.exception;

/**
 * 自定义异常类，用于处理数据模型未找到的情况
 */
public class DataModelNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public DataModelNotFoundException(String message) {
        super(message);
    }
    
}

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据模型实体类
 */
@Entity
@Table(name = "data_models")
public class DataModel {
    @Id
    private String id;
    private String name;
    private String description;
    
    // 省略getter和setter方法
    
    public DataModel() {
        // 默认构造函数
    }
    
    // 省略其他方法
}

package com.example.demo.repository;

import com.example.demo.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 数据模型仓库接口
 */
@Repository
public interface DataModelRepository extends JpaRepository<DataModel, String> {
    
}

package com.example.demo.service.impl;

import com.example.demo.exception.DataModelNotFoundException;
import com.example.demo.model.DataModel;
import com.example.demo.repository.DataModelRepository;
import com.example.demo.model.DataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * 数据模型服务实现
 */
@Service
public class DataModelServiceImpl implements DataModelService {
    
    private final DataModelRepository dataModelRepository;
    
    @Autowired
    public DataModelServiceImpl(DataModelRepository dataModelRepository) {
        this.dataModelRepository = dataModelRepository;
    }
    
    @Override
    public DataModel getDataModelById(String modelId) {
        return dataModelRepository.findById(modelId).orElseThrow("=> new DataModelNotFoundException("Data model not found with id: " + modelId)
    }
    
    @Override
    public DataModel saveOrUpdateDataModel(DataModel model) {
        return dataModelRepository.save(model);
    }
    
    @Override
    public void deleteDataModelById(String modelId) throws DataModelNotFoundException {
        DataModel model = dataModelRepository.findById(modelId).orElseThrow("=> new DataModelNotFoundException("Data model not found with id: " + modelId)
        dataModelRepository.delete(model);
    }
}