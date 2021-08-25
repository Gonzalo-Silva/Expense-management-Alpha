package com.example.demo.servicios;

import java.util.*;
import javax.transaction.Transactional;
import com.example.demo.entidades.Categoria;
import com.example.demo.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;


    @Transactional
    public void crearCategoria(String nombre){
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        categoriaRepositorio.save(categoria);
    }


    @Transactional
    public List<Categoria> buscarTodos() {
        return categoriaRepositorio.findAll();
    }

    @Transactional
    public void eliminar(Long idCat) {
        categoriaRepositorio.deleteById(idCat);
    }

    @Transactional
    public void modificar(Long idCat, String nombre) {
        categoriaRepositorio.modificar(idCat, nombre);
    
    }

    @Transactional
    public Categoria buscarPorIdCat(Long idCat) {
        Optional<Categoria> categoriaOptional = categoriaRepositorio.findById(idCat);
        return categoriaOptional.orElse(null);
    }


}
