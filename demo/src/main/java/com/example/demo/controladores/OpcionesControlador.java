package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpcionesControlador {

    /*@GetMapping("/")
    public String Index() {
        return "index";
    }*/
    
    @GetMapping("/Home")
    public ModelAndView home() {
        return new ModelAndView("Home");
    }

    @GetMapping("/control")
    public ModelAndView inicio() {
        return new ModelAndView("Control");
    }


    @GetMapping("/ayuda")
    public ModelAndView ayuda() {
        return new ModelAndView("help");
    }

    @GetMapping("/registro")
    public ModelAndView registro() {
        return new ModelAndView("registro-usuario");
    }


}
