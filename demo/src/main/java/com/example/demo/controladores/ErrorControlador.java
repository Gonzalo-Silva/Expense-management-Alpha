package com.example.demo.controladores;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorControlador {
    
    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("error", e.getClass().getSimpleName());
        return mav;

    }

}
