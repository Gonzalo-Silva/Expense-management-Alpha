package com.example.demo.controladores;

import com.example.demo.entidades.Categoria;
import com.example.demo.entidades.FormasPago;
import com.example.demo.entidades.Ingreso;
import com.example.demo.servicios.CategoriaServicio;
import com.example.demo.servicios.FormasPagoServicio;
import com.example.demo.servicios.IngresoServicio;
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
@RequestMapping("/ingresos")
public class IngresoControlador {
   
    @Autowired
    private IngresoServicio ingresoServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private FormasPagoServicio formasPagoServicio;
    

    @GetMapping() 
    public ModelAndView buscarTodos() {
        ModelAndView mav = new ModelAndView("ingresos-todos");
        List<Ingreso> ingresos = ingresoServicio.buscarTodos();
        mav.addObject("ingresos", ingresos); 
        return mav;
    }

    @GetMapping("/crear") 
    public ModelAndView mostrarFormulario(){
        ModelAndView mav = new ModelAndView("ingresos-formulario");
        mav.addObject("ingreso", new Ingreso());
        mav.addObject("categorias", categoriaServicio.buscarTodos());
        mav.addObject("formasPagos", formasPagoServicio.buscarTodos());
        mav.addObject("title", "Crear Ingreso");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String descripcion, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam String nota, @RequestParam Long monto,
        @RequestParam("categoria") Long idCat, @RequestParam("formasPago") Long idPago) { 
        ingresoServicio.crearIngreso(fecha, descripcion, monto, nota, idCat, idPago);
        return new RedirectView("/ingresos");
    }

    
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id) {
        ingresoServicio.eliminar(id);
        return new RedirectView("/ingresos");
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("ingresos-formulario");
        mav.addObject("ingreso", ingresoServicio.buscarPorId(id));
        mav.addObject("categorias", categoriaServicio.buscarTodos());
        mav.addObject("formasPagos", formasPagoServicio.buscarTodos());
        mav.addObject("title", "Editar Ingreso");
        mav.addObject("action", "modificar");
        return mav;
    }

    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long id, @RequestParam String descripcion, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam String nota, @RequestParam Long monto,
    @RequestParam("categoria") Categoria categoria, @RequestParam("formasPago") FormasPago formasPago) {
        ingresoServicio.modificar(id, descripcion, fecha, nota, monto, categoria, formasPago);
        return new RedirectView("/ingresos");
    }

    /*
    @GetMapping("/buscar/{categoria}")
    public ModelAndView buscarPorCategoria(@PathVariable Categoria categoria) {
        ModelAndView mav = new ModelAndView("ingresos-todos");
        List<Ingreso> ingresos = ingresoServicio.buscarPorCategoria(categoria);
        mav.addObject("ingresos", ingresos); 
        List<Categoria> categorias = categoriaServicio.buscarTodos();
        mav.addObject("categorias", categorias); 
        return mav;
    }

    
    @GetMapping("/buscar/{descripcion}") 
    public ModelAndView buscarPorDescripcion(@PathVariable String descripcion) {
        ModelAndView mav = new ModelAndView("ingresos-descripcion");
        List<Ingreso> ingresos = ingresoServicio.buscarPorDescripcion(descripcion);
        mav.addObject("ingresos", ingresos); 
        return mav;
    }
*/

}
