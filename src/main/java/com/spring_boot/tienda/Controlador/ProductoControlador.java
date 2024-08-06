package com.spring_boot.tienda.Controlador;

import com.spring_boot.tienda.Entidad.Cliente;
import com.spring_boot.tienda.Entidad.Producto;
import com.spring_boot.tienda.Servicio.ClienteServicio;
import com.spring_boot.tienda.Servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/productos")
    public String mostrarProductos(@RequestParam(name = "buscarProducto", required = false, defaultValue = "") String buscarProducto, Model model) {
        List<Producto> productos = productoServicio.buscarProductoNombre(buscarProducto);
        model.addAttribute("buscarProducto", buscarProducto);
        model.addAttribute("productos", productos);
        return "/Producto/vistaProductos";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        List<Cliente> clientes = clienteServicio.listarClientes();  // Asegúrate de que esto no es nulo
        model.addAttribute("clientes", clientes);
        return "/Producto/formularioProducto";
    }

    @PostMapping("/guardar/producto")
    public String crearProducto(@ModelAttribute Producto producto) {
        productoServicio.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoServicio.buscarProductoId(id);
        model.addAttribute("producto", producto);
        return "Producto/formularioProducto";
    }

    @GetMapping("/eliminar/producto/{id}")
    public String borrar(@PathVariable Long id) {
        productoServicio.eliminarProducto(id);
        return "redirect:/productos";
    }
    @PostMapping("/guardar/cliente")
    public String guardarCliente(@RequestParam String nombre, @RequestParam String apellido) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
        clienteServicio.guardarCliente(nuevoCliente);
        return "redirect:/formulario"; // Redirige al formulario de producto o cualquier otra página
    }
}
