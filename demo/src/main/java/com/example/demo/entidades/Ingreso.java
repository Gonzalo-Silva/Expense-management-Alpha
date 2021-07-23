package com.example.demo.entidades;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column
    private String descripcion;

    @Column
    private Long monto;
    
    @Column
    private String nota;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private FormasPago formasPago;

    public Ingreso() {
    }

    public Ingreso(Long monto, Categoria categoria) {
        this.monto = monto;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public FormasPago getFormasPago() {
        return formasPago;
    }

    public void setFormasPago(FormasPago formasPago) {
        this.formasPago = formasPago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingreso ingreso = (Ingreso) o;
        return id.equals(ingreso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    
}
