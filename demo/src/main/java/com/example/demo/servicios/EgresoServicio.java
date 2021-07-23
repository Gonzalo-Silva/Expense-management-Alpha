package com.example.demo.servicios;

import java.util.*;
import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.FormasPago;
import com.example.demo.entidades.Egreso;
import com.example.demo.repositorios.CategoriaRepositorio;
import com.example.demo.repositorios.FormasPagoRepositorio;
import com.example.demo.repositorios.EgresoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class EgresoServicio {
    @Autowired
    private EgresoRepositorio egresoRepositorio;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    @Autowired
    private FormasPagoRepositorio formasPagoRepositorio;

    @Transactional
    public void crearEgreso(Date fecha, String descripcion, Long monto, String nota, Long idCat, Long idPago){
        Egreso egreso = new Egreso();

        egreso.setFecha(fecha);
        egreso.setDescripcion(descripcion);
        egreso.setMonto(monto);
        egreso.setNota(nota);
        egreso.setCategoria(categoriaRepositorio.findById(idCat).orElse(null));
        egreso.setFormasPago(formasPagoRepositorio.findById(idPago).orElse(null));

        egresoRepositorio.save(egreso);
    }


    @Transactional
    public List<Egreso> buscarTodos() {
        return egresoRepositorio.findAll();
    }

    @Transactional
    public Egreso buscarPorId(Long id) {
        Optional<Egreso> egresoOptional = egresoRepositorio.findById(id);
        return egresoOptional.orElse(null);
    }
@Transactional
    public void modificar(Long id, String descripcion, Date fecha, String nota, Long monto, Categoria categoria, FormasPago formasPago) {
        egresoRepositorio.modificar(id, descripcion, fecha, nota, monto, categoria, formasPago);

    }

    @Transactional
    public void eliminar(Long id) {
        egresoRepositorio.deleteById(id);
    }

    @Transactional
    public Long egresosTodos() {
        return egresoRepositorio.egresosTodos();
    }

    @Transactional
    public Long egresosTodos(Date desde, Date hasta) {
 
        if(desde != null && hasta != null){
        return egresoRepositorio.egresosTodos(desde, hasta);
        } 
        return egresoRepositorio.egresosTodos();
    }

    @Transactional
    public List<Egreso> egresosCategoria(Date desde, Date hasta) {

        if(desde != null && hasta != null){
        return egresoRepositorio.egresosCategoria(desde, hasta);
        } 
        return egresoRepositorio.egresosCategoria();
    }



}