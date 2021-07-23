package com.example.demo.controladores;

import com.example.demo.entidades.Egreso;
import com.example.demo.entidades.Ingreso;
import com.example.demo.servicios.EgresoServicio;
import com.example.demo.servicios.IngresoServicio;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
    @RequestMapping("/estadisticas")
public class EstadisticasControlador {


        @Autowired
        private IngresoServicio ingresoServicio;
        @Autowired
        private EgresoServicio egresoServicio;


        @GetMapping() 
        public ModelAndView resultados(@RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde, @RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta) {
            ModelAndView mav = new ModelAndView("estadisticas");

            //Mostrar resultados globales:
            Long ingresos = ingresoServicio.ingresosTodos(desde, hasta);
            Long egresos = egresoServicio.egresosTodos();
            Long resultadosGlobales = ingresos - egresos;
            mav.addObject("ingresos", ingresos);
            mav.addObject("egresos", egresos); 
            mav.addObject("resultadosGlobales", resultadosGlobales);
//Mostrar ingresos por categoría:
            List<Ingreso>  ingresosCategoria = ingresoServicio.ingresosCategoria(desde, hasta);
            mav.addObject("ingresosCategoria", ingresosCategoria); 

            //Mostrar egresos por categoría:
            List<Egreso>  egresosCategoria = egresoServicio.egresosCategoria(desde, hasta);
            mav.addObject("egresosCategoria", egresosCategoria); 

            return mav;
        }



    }