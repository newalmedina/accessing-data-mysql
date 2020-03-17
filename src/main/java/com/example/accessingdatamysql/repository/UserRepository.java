package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Material;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.model.User;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends CrudRepository<User, Integer> {

    //SELECT WITH order date
    @Query(
            value = "SELECT * FROM user order by fecha_creacion  desc",
            nativeQuery = true)
    Collection<User> selectOrderCreationDate();
    
    //SELECT WITH LIKE NAME
    @Query(
            value = "SELECT * FROM user u WHERE u.name like CONCAT('%',?1,'%')",
            nativeQuery = true)
    Collection<User> containName(String name);

   
    //FIND BYNAME       
    public List<User> findByName(String name);
    
      //SELECT  LAST ID
    @Query(
            value = "SELECT * FROM user order by id desc limit 1",
            nativeQuery = true)
    Optional<User> findLastId();

      //SELECT WITH LIKE EMAIL
    @Query(
            value =  "SELECT * FROM user u WHERE u.email like CONCAT('%',?1,'%')",
            nativeQuery = true)
    Collection<User> containEmail(String email);
    
      //SELECT WITH LIKE YEARCReation
    @Query(
            value =  "SELECT * FROM user u  where year(fecha_creacion)=?1",
            nativeQuery = true)
    Collection<User> yearCreation(String year);
    
      //SELECT WITH LIKE YEARCReation between
    @Query(
            value =  "SELECT * FROM user u  where year(fecha_creacion) between ?1 and ?2",
            nativeQuery = true)
    Collection<User> yearCreationBetween(String year1, String year2);

      //SELECT WITH NAME AND EMAIL EQUAL
    @Query(
            value =  "SELECT * FROM user u WHERE u.email =?1 and u.name=?2",
            nativeQuery = true)
            
    Optional<User> login(String email,String name);
    
    //UPDATE
    
    @Modifying
      @Transactional
    @Query(
            value = "UPDATE user SET email=?2, name=?3,fecha_actualizacion=?4, fecha_nacimiento=?5, departamento_id=?6   where id=?1",
            nativeQuery = true)
    int updateregister(int id, String email, String name, String fecha_actualizacion, String fecha_naimiento, int departamento_id);
    
    @Modifying
      @Transactional
    @Query(
            value = "insert into usuarios_materiales (id_usuario, id_material) values (?1,?2)",
            nativeQuery = true)
    int insertUserMaterial(User idUsuario, Material idaterial);
    
    //DELETE ALL
     @Modifying
      @Transactional
      @Query(
            value = "DELETE FROM user ",
            nativeQuery = true)
        int deleteAllRegisters();
        
}
