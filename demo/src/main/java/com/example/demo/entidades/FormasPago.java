package com.example.demo.entidades;

import java.util.List;

import javax.persistence.*;

@Entity
public class FormasPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @Column
    private String tipo;

    @OneToMany(mappedBy = "formasPago")
    private List<Ingreso> ingresos;

    @OneToMany(mappedBy = "formasPago")
    private List<Egreso> egresos;
    

    public FormasPago() {
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPago == null) ? 0 : idPago.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FormasPago other = (FormasPago) obj;
        if (idPago == null) {
            if (other.idPago != null)
                return false;
        } else if (!idPago.equals(other.idPago))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FormasPago [egresos=" + egresos + ", idPago=" + idPago + ", ingresos=" + ingresos + ", tipo=" + tipo
                + "]";
    }



   

}
