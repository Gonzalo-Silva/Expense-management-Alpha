package com.example.demo.controladores;

import com.example.demo.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;


    @GetMapping("/crear-usuario")
    public ModelAndView mostrarFormulario() {
        return new ModelAndView("registro-usuario");
    }
    @PostMapping("/guardar-usuario")
    public RedirectView guardar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @RequestParam String mail ,
    @RequestParam String contrasena) {
        usuarioServicio.crear(nombre, apellido, dni, mail, contrasena);
        return new RedirectView("/Home");
    }
}