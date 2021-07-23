package com.example.demo.controladores;

import java.util.*;
import com.example.demo.entidades.Categoria;
import com.example.demo.servicios.CategoriaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping() 
    public ModelAndView buscarTodos() {
        ModelAndView mav = new ModelAndView("categorias-todas");
        List<Categoria> categorias = categoriaServicio.buscarTodos();
        mav.addObject("categorias", categorias); 
        return mav;
    }

    @GetMapping("/crear") 
    public ModelAndView mostrarFormulario(){
        ModelAndView mav = new ModelAndView("categoria-formulario");
        mav.addObject("categoria", new Categoria());
        mav.addObject("title", "Crear Categoria");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) { 
        categoriaServicio.crearCategoria(nombre);
        return new RedirectView("/categorias");
    }

    @PostMapping("/eliminar/{idCat}")
    public RedirectView eliminar(@PathVariable Long idCat) {
        categoriaServicio.eliminar(idCat);
        return new RedirectView("/categorias");
    }


    @GetMapping("/editar/{idCat}")
    public ModelAndView editar(@PathVariable Long idCat) {
        ModelAndView mav = new ModelAndView("categoria-formulario");
        mav.addObject("categoria", categoriaServicio.buscarPorIdCat(idCat));
        mav.addObject("title", "Editar Categoría");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long idCat, @RequestParam String nombre) {
        categoriaServicio.modificar(idCat, nombre);
        return new RedirectView("/categorias");
    }


}
