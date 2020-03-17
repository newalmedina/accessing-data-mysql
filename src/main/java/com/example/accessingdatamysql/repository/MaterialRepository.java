
package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Material;
import org.springframework.data.repository.CrudRepository;


public interface MaterialRepository extends CrudRepository<Material, Integer> {
    
}
