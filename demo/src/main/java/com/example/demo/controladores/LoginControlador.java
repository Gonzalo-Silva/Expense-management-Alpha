package com.example.demo.controladores;


import com.example.demo.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class LoginControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String inicio(){
        return ("/index");
    }

    @GetMapping("/login")
    public String IniciarSesion(){
        return ("/login");
    }

    @PostMapping("/login")
    public RedirectView Login(@RequestParam String mail, @RequestParam String contrasena) throws Exception{
        usuarioServicio.IniciarSesion(mail, contrasena);
        return new RedirectView("/Home");
    }

}
