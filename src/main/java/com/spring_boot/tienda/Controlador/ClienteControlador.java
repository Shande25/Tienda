package com.spring_boot.tienda.Controlador;

import com.spring_boot.tienda.Entidad.Cliente;
import com.spring_boot.tienda.Entidad.Producto;
import com.spring_boot.tienda.Servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClienteControlador {
    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/productos/{clienteId}")
    public String mostrarProductosByCliente(@PathVariable Long clienteId, Model model) {
        Cliente cliente = clienteServicio.obtenerClientePorId(clienteId);
        if (cliente == null) {
            // Redirige a una página de error o muestra un mensaje adecuado
            return "error"; // Asegúrate de que haya una vista llamada "error.html"
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
        return "Cliente/vistaCliente"; // Nombre de la vista Thymeleaf
    }
}
