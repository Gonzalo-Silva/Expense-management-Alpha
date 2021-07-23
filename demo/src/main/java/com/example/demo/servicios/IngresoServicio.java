package com.example.demo.servicios;

import java.util.*;
import javax.transaction.Transactional;
import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.FormasPago;
import com.example.demo.entidades.Ingreso;
import com.example.demo.repositorios.CategoriaRepositorio;
import com.example.demo.repositorios.FormasPagoRepositorio;
import com.example.demo.repositorios.IngresoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoServicio {

    @Autowired
    private IngresoRepositorio ingresoRepositorio;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    @Autowired
    private FormasPagoRepositorio formasPagoRepositorio;

    @Transactional
    public void crearIngreso(Date fecha, String descripcion, Long monto, String nota, Long idCat, Long idPago){
        Ingreso ingreso = new Ingreso();

        ingreso.setFecha(fecha);
        ingreso.setDescripcion(descripcion);
        ingreso.setMonto(monto);
        ingreso.setNota(nota);
        ingreso.setCategoria(categoriaRepositorio.findById(idCat).orElse(null));
        ingreso.setFormasPago(formasPagoRepositorio.findById(idPago).orElse(null));

        ingresoRepositorio.save(ingreso);
    }


    @Transactional
    public List<Ingreso> buscarTodos() {
        return ingresoRepositorio.findAll();
    }
@Transactional
    public Ingreso buscarPorId(Long id) {
        Optional<Ingreso> ingresoOptional = ingresoRepositorio.findById(id);
        return ingresoOptional.orElse(null);
    }


        @Transactional
    public void modificar(Long id, String descripcion, Date fecha, String nota, Long monto, Categoria categoria, FormasPago formasPago) {
        ingresoRepositorio.modificar(id, descripcion, fecha, nota, monto, categoria, formasPago);

    }

    @Transactional
    public void eliminar(Long id) {
        ingresoRepositorio.deleteById(id);
    }


    @Transactional
    public Long ingresosTodos(Date desde, Date hasta) {
 
        if(desde != null && hasta != null){
        return ingresoRepositorio.ingresosTodos(desde, hasta);
        } 
        return ingresoRepositorio.ingresosTodos();
    }

    @Transactional
    public List<Ingreso> ingresosCategoria(Date desde, Date hasta) {

        if(desde != null && hasta != null){
        return ingresoRepositorio.ingresosCategoria(desde, hasta);
        } 
        return ingresoRepositorio.ingresosCategoria();
    }


}