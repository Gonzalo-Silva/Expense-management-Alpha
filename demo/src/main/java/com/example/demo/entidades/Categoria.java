package com.example.demo.entidades;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;

    @Column
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<Ingreso> ingresos;

    @OneToMany(mappedBy = "categoria")
    private List<Egreso> egresos;

    public Categoria() {
    }

    public Long getIdCat() {
        return idCat;
    }


    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public List<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<Egreso> egresos) {
        this.egresos = egresos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idCat.equals(categoria.idCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCat);
    }

    @Override
    public String toString() {
        return "Categoria [egresos=" + egresos + ", idCat=" + idCat + ", ingresos=" + ingresos + ", nombre=" + nombre
                + "]";
    }
}