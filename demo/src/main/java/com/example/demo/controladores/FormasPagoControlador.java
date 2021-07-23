package com.example.demo.controladores;

import java.util.*;
import com.example.demo.entidades.FormasPago;
import com.example.demo.servicios.FormasPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/formasPagos")
public class FormasPagoControlador {

    @Autowired
    private FormasPagoServicio formasPagoServicio;

    @GetMapping() 
    public ModelAndView buscarTodos() {
        ModelAndView mav = new ModelAndView("formasPago-todas");
        List<FormasPago> formasPagos = formasPagoServicio.buscarTodos();
        mav.addObject("formasPagos", formasPagos); 
        return mav;
    }

    @GetMapping("/crear") 
    public ModelAndView mostrarFormulario(){
        ModelAndView mav = new ModelAndView("formasPago-formulario");
        mav.addObject("formasPago", new FormasPago());
        mav.addObject("title", "Crear Forma de Pago");
        mav.addObject("action", "guardar");
        return mav;
    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String tipo) { 
        formasPagoServicio.crearFormasPago(tipo);
        return new RedirectView("/formasPagos");
    }

    @PostMapping("/eliminar/{idPago}")
    public RedirectView eliminar(@PathVariable Long idPago) {
        formasPagoServicio.eliminar(idPago);
        return new RedirectView("/formasPagos");
    }

    @GetMapping("/editar/{idPago}")
    public ModelAndView editar(@PathVariable Long idPago) {
        ModelAndView mav = new ModelAndView("formasPago-formulario");
        mav.addObject("formasPago", formasPagoServicio.buscarPorIdPago(idPago));
        mav.addObject("title", "Editar Forma de Pago");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam Long idPago, @RequestParam String tipo) {
        formasPagoServicio.modificar(idPago, tipo);
        return new RedirectView("/formasPagos");
    }

    
}
