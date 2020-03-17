
package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Departamento;
import org.springframework.data.repository.CrudRepository;

public interface DepartamentoRepository extends CrudRepository<Departamento, Integer>  {
    
}
