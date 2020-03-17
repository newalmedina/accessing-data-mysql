package com.example.accessingdatamysql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date FechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    
    //estableiendo  many to one
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Departamento departamento;
    
   
    //estableciendo may to many
  
    @ManyToMany(cascade = {CascadeType.ALL})
    //@JoinTable(name = "EM_FK_ELEMENT_IDIOMA", joinColumns = @JoinColumn(name = "ELEMENT_ID", referencedColumnName = "identificadorElement", nullable = false), inverseJoinColumns = @JoinColumn(name = "IDIOMA_ID", referencedColumnName = "id", nullable = false))
   
    @JoinTable(name="Usuarios_Materiales", joinColumns={@JoinColumn(name="idUsuario")}, inverseJoinColumns={@JoinColumn(name="idMaterial")})
     private Set<Material> materiales=new HashSet();
 
          
          
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(Set<Material> materiales) {
        this.materiales = materiales;
    }
    
    
}
