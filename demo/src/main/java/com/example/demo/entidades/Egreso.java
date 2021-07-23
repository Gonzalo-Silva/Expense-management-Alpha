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
public class Egreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Egreso() {
    }

    public Egreso(Long monto, Categoria categoria) {
        this.monto = monto;
        this.categoria = categoria;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Egreso egreso = (Egreso) o;
        return id.equals(egreso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    
    

    
}
