package com.spring_boot.tienda.Controlador;

import com.spring_boot.tienda.Entidad.Cliente;
import com.spring_boot.tienda.Entidad.Producto;
import com.spring_boot.tienda.Repositorio.ClienteRepositorio;
import com.spring_boot.tienda.Repositorio.ProductoRepositorio;
import com.spring_boot.tienda.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping("/productos/{clienteId}")
    public String mostrarProductosByCliente(@PathVariable Long clienteId, Model model) {
        Cliente cliente = clienteServicio.obtenerClientePorId(clienteId);
        if (cliente == null) {
            return "error";
        }
        List<Producto> productos = clienteServicio.buscarProductoByClienteId(clienteId);
        model.addAttribute("cliente", cliente);
        model.addAttribute("productos", productos);
        return "Cliente/vistaCliente";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
        List<Cliente> clientes = clienteServicio.listarClientes();
        model.addAttribute("clientes", clientes);
        return "Cliente/vistaCliente";
    }

    @GetMapping("/productos/agregar/{clienteId}")
    public String mostrarAgregarProducto(@PathVariable Long clienteId, Model model) {
        Cliente cliente = clienteServicio.obtenerClientePorId(clienteId);
        if (cliente == null) {
            return "error";
        }
        List<Producto> productos = productoRepositorio.findAll();
        model.addAttribute("cliente", cliente);
        model.addAttribute("productos", productos);
        return "Producto/agregarCliente";
    }

    @PostMapping("/agregar/producto/{clienteId}")
    public String agregarProducto(@PathVariable("clienteId") Long clienteId, @RequestParam("productoId") Long productoId) {
        Cliente cliente = clienteRepositorio.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        Producto producto = productoRepositorio.findById(productoId).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        cliente.getProductos().add(producto);
        clienteRepositorio.save(cliente);

        return "redirect:/productos/" + clienteId;
    }

}
