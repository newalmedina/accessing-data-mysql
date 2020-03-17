package com.example.accessingdatamysql.controller;

import ch.qos.logback.core.CoreConstants;
import com.example.accessingdatamysql.model.Departamento;
import com.example.accessingdatamysql.model.Material;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.accessingdatamysql.model.User;
import com.example.accessingdatamysql.repository.MaterialRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/demo")

public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MaterialRepository materRepository;

    
 @GetMapping(path = "/getmaterial") // Map ONLY POST Requests
    public @ResponseBody
    Iterable<Material> getMaterial( ) {
        return materRepository.findAll();
    
    }
 @GetMapping(path = "/selectOrderCreationDate") // Map ONLY POST Requests
    public @ResponseBody
    Collection selectOrderCreationDate( ) {
        return userRepository.selectOrderCreationDate();
    
    }
 @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addUser( @RequestParam String name,
            @RequestParam String email, @RequestParam String fechaNacimiento,  @RequestParam Departamento departamentoId) throws ParseException {

        User n = new User();
        n.setName(name);
        n.setEmail(email);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = format.parse(fechaNacimiento);
        
        n.setFechaNacimiento(fecha);
        
        n.setFechaCreacion(new Date());
           

       n.setDepartamento(departamentoId);

        userRepository.save(n);
        return "Guardado";
    }
 @PostMapping(path = "/addMaterialUser") // Map ONLY POST Requests
    public @ResponseBody
    String addMaterialUser( @RequestParam User userId,
            @RequestParam Material materialId){

    
        int resultado = userRepository.insertUserMaterial(userId,materialId);
        if(resultado ==1)
            return "Material Asignado A usuario";
        
        return "no se ha podido asignar material al usuario";
    }
    
    
    @PostMapping(path = "/edit") // Map ONLY POST Requests
    public @ResponseBody
    String editUser(@RequestParam int id, @RequestParam String name, @RequestParam String email,
            @RequestParam String fechaNacimiento, @RequestParam String fechaActualizacion, 
            @RequestParam int  departamentoId) throws ParseException {

        /*User n = new User();
        n.setId(id);
        n.setName(name);
        n.setEmail(email);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = format.parse(fechaNacimiento);
        
        n.setFechaNacimiento(fecha);
        
        Date date = new Date();
        n.setFechaActualizacion(date);
        
        n.setDepartamento(departamentoId);*/

        int resultado = userRepository.updateregister(id, email, name, fechaActualizacion, fechaNacimiento,departamentoId);
        if (resultado ==1)
            return "Actualizado" ;
        else
            return "no se ha podido actualizar";
    }

    @PostMapping(path = "/add2") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser2(@RequestBody User user) {

        userRepository.save(user);
        return "Saved";
    }

    @PostMapping(path = "/edit2") // Map ONLY POST Requests
    public @ResponseBody
    String editUser2(@RequestBody User user) {

        userRepository.save(user);
        return "actualizado";
    }

    @PostMapping(path = "/delete") // Map ONLY POST Requests
    public @ResponseBody
    String eliminarUser(@RequestParam int id) {

        userRepository.deleteById(id);
        return "Eliminado";
    }
    
    @PostMapping(path = "/deleteAll") // Map ONLY POST Requests
    public @ResponseBody
    String deleteAllRegisters() {

        userRepository.deleteAllRegisters();
        return "Eliminado";
    }

    @PostMapping(path = "/delete2") // Map ONLY POST Requests
    public @ResponseBody
    String eliminarUser2(@RequestBody User user) {

        userRepository.delete(user);
        return "Eliminado";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/findId") // Map ONLY POST Requests
    public @ResponseBody
    Optional<User> findId(@RequestParam int id
    ) {
        return userRepository.findById(id);
    }

    @GetMapping(path = "/findName") // Map ONLY POST Requests
    public @ResponseBody
    List<User> findName(@RequestParam String nombre
    ) {
        return userRepository.findByName(nombre);
    }

    @GetMapping(path = "/containName") // Map ONLY POST Requests
    public @ResponseBody
    Collection<User> containName(@RequestParam String nombre
    ) {
        return userRepository.containName(nombre);
    }

    @GetMapping(path = "/findLastId") // Map ONLY POST Requests
    public @ResponseBody
    Optional<User> findLastId() {
        return userRepository.findLastId();
    }

    @GetMapping(path = "/containEmail") // Map ONLY POST Requests
    public @ResponseBody
    Collection<User> containEmail(@RequestParam String email
    ) {
        return userRepository.containEmail(email);
    }
    
    @GetMapping(path = "/login") // Map ONLY POST Requests
    public @ResponseBody
    Optional<User> login(@RequestParam String email,@RequestParam String name) {
        return userRepository.login(email, name);
    }
    
    @GetMapping(path = "/findYearCreation") // Map ONLY POST Requests
    public @ResponseBody
    Collection<User> yearCreation(@RequestParam String year) {
        return userRepository.yearCreation(year);
    }
    @GetMapping(path = "/findYearCreationBetween") // Map ONLY POST Requests
    public @ResponseBody
    Collection<User> yearCreationBetween(@RequestParam String yearIni, @RequestParam String yearFinal) {
        return userRepository.yearCreationBetween(yearIni,yearFinal);
    }

}
