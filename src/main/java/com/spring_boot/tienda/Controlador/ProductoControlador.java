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
        return "Producto/vistaProductos"; // Verifica que esta plantilla exista
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        List<Cliente> clientes = clienteServicio.listarClientes();
        model.addAttribute("clientes", clientes);
        return "Producto/formularioProducto"; // Verifica que esta plantilla exista
    }

    @PostMapping("/guardar/producto")
    public String crearProducto(@ModelAttribute Producto producto) {
        productoServicio.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoServicio.buscarProductoId(id);
        model.addAttribute("producto", producto.orElse(null));
        return "Producto/formularioProducto"; // Verifica que esta plantilla exista
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
        return "redirect:/formulario"; // Ajusta según la ruta correcta
    }

    @GetMapping("/agregarCliente")
    public String agregarClienteForm(Model model) {
        Cliente cliente = new Cliente(); // Crea un nuevo cliente o recupera uno de la base de datos
        model.addAttribute("cliente", cliente);
        return "Producto/agregarCliente"; // Asegúrate de que este nombre coincida con el archivo HTML
    }

    @PostMapping("/agregar/{clienteId}")
    public String agregarProducto(@PathVariable Long clienteId, @RequestParam Long productoId) {
        // Implementa la lógica para agregar el producto al cliente
        return "redirect:/productos/" + clienteId; // Ajusta según la ruta correcta
    }
}