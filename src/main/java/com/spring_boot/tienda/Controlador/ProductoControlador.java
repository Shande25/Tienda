package com.spring_boot.tienda.Controlador;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.spring_boot.tienda.Entidad.Producto;
import com.spring_boot.tienda.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;
    //Leer
    @GetMapping("/productos")
    public String mostrarProductos(Model model){
        List<Producto> productos = productoServicio.listaProductos();
        model.addAttribute("productos", productos);
        return "/Producto/vistaProductos";
    }
//Crear
    @GetMapping("/formulario")
    public String formularioProducto(Model model){
        model.addAttribute("producto", new Producto());
        return "/Producto/formularioProducto";
    }
    @PostMapping("/guardar/producto")
    public String crearProducto(Producto producto){
productoServicio.guardarProducto(producto);
return "redirect:/productos";

    }
    //actualizar
@GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable Long id, Model model){
    Optional<Producto> producto=productoServicio.buscarProducto(id);
    model.addAttribute("producto",producto);
    return "Producto/formularioProducto";
}
//Eliminar
    @GetMapping("/eliminar/producto/{id}")
    public String borrar(@PathVariable Long id){
productoServicio.eliminarProducto(id);
return "redirect:/productos";
    }
}
