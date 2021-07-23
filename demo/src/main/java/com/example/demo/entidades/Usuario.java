package com.example.demo.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
 @Column   
 private String nombre;
 @Column
 private String apellido;
 @Column
 private Long dni;

 @Column (unique = true) //para que no se repitan los usuarios
 private String mail;
 @Column 
 private String contrasena;
 @Temporal(TemporalType.TIMESTAMP)
 private Date alta;

 @Temporal(TemporalType.TIMESTAMP)
 private Date baja;
 
public Usuario() {
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getApellido() {
    return apellido;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

public Long getDni() {
    return dni;
}

public void setDni(Long dni) {
    this.dni = dni;
}

public String getMail() {
    return mail;
}

public void setMail(String mail) {
    this.mail = mail;
}

public String getContrasena() {
    return contrasena;
}

public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
}

public Date getAlta() {
    return alta;
}

public void setAlta(Date alta) {
    this.alta = alta;
}

public Date getBaja() {
    return baja;
}

public void setBaja(Date baja) {
    this.baja = baja;
}    


}
