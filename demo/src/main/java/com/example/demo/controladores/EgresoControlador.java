package com.example.demo.controladores;

import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.FormasPago;
import com.example.demo.entidades.Egreso;
import com.example.demo.servicios.CategoriaServicio;
import com.example.demo.servicios.FormasPagoServicio;
import com.example.demo.servicios.EgresoServicio;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/egresos")
public class EgresoControlador {
    
    
    @Autowired
    private EgresoServicio egresoServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private FormasPagoServicio formasPagoServicio;

    @GetMapping() 
    public ModelAndView buscarTodos() {
        ModelAndView mav = new ModelAndView("egresos-todos");
        List<Egreso> egresos = egresoServicio.buscarTodos();
        mav.addObject("egresos", egresos); 
        return mav;
    }

    @GetMapping("/crear") 
    public ModelAndView mostrarFormulario(){
        ModelAndView mav = new ModelAndView("egresos-formulario");
        mav.addObject("egreso", new Egreso());
        mav.addObject("categorias", categoriaServicio.buscarTodos());
        mav.addObject("formasPagos", formasPagoServicio.buscarTodos());
        mav.addObject("title", "Crear Egreso");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String descripcion, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam String nota, @RequestParam Long monto,
        @RequestParam("categoria") Long idCat, @RequestParam("formasPago") Long idPago) { 
        egresoServicio.crearEgreso(fecha, descripcion, monto, nota, idCat, idPago);
        return new RedirectView("/egresos");
    }
    
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        egresoServicio.eliminar(id);
        return new RedirectView("/egresos");
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("egresos-formulario");
        mav.addObject("egreso", egresoServicio.buscarPorId(id));
        mav.addObject("categorias", categoriaServicio.buscarTodos());
        mav.addObject("formasPagos", formasPagoServicio.buscarTodos());
        mav.addObject("title", "Editar Egreso");
        mav.addObject("action", "modificar");
        return mav;
    }

    
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long id, @RequestParam String descripcion, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam String nota, @RequestParam Long monto,
    @RequestParam("categoria") Categoria categoria, @RequestParam("formasPago") FormasPago formasPago) {
        egresoServicio.modificar(id, descripcion, fecha, nota, monto, categoria, formasPago);
        return new RedirectView("/egresos");
    }


}
