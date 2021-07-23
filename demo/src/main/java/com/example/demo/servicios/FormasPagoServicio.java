package com.example.demo.servicios;

import java.util.*;
import javax.transaction.Transactional;
import com.example.demo.entidades.FormasPago;
import com.example.demo.repositorios.FormasPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormasPagoServicio {

    @Autowired
    private FormasPagoRepositorio formasPagoRepositorio;


    @Transactional
    public void crearFormasPago(String tipo){
        FormasPago formasPago = new FormasPago();
        formasPago.setTipo(tipo);

        formasPagoRepositorio.save(formasPago);
    }


    @Transactional
    public List<FormasPago> buscarTodos() {
        return formasPagoRepositorio.findAll();
    }

    @Transactional
    public void eliminar(Long idPago) {
        formasPagoRepositorio.deleteById(idPago);
    }

    @Transactional
    public void modificar(Long idPago, String tipo) {
        formasPagoRepositorio.modificar(idPago, tipo);
    
    }

    @Transactional
    public FormasPago buscarPorIdPago(Long idPago) {
        Optional<FormasPago> formasPagoOptional = formasPagoRepositorio.findById(idPago);
        return formasPagoOptional.orElse(null);
    }
    
}
