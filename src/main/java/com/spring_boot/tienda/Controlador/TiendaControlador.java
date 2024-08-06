package com.spring_boot.tienda.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaControlador {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
