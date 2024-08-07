package com.spring_boot.tienda.Servicio;

import com.spring_boot.tienda.Entidad.Cliente;
import com.spring_boot.tienda.Entidad.Producto;
import com.spring_boot.tienda.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Producto> buscarProductoByClienteId(Long clienteId) {
        return clienteRepositorio.findById(clienteId)
                .map(Cliente::getProductos)
                .orElse(List.of());
    }

    public Cliente obtenerClientePorId(Long clienteId) {
        return clienteRepositorio.findById(clienteId).orElse(null);
    }

    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }
}
