package com.spring_boot.tienda.Servicio;

import com.spring_boot.tienda.Entidad.Producto;
import com.spring_boot.tienda.Repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> listaProductos() {
        return productoRepositorio.findAll();
    }

    public List<Producto> buscarProductoNombre(String buscarProducto) {
        if (buscarProducto == null || buscarProducto.isEmpty()) {
            return productoRepositorio.findAll();
        } else {
            return productoRepositorio.findByNombreContainingIgnoreCase(buscarProducto);
        }
    }

    public Optional<Producto> buscarProductoId(Long id) {
        return productoRepositorio.findById(id);
    }

    public void guardarProducto(Producto producto) {
        productoRepositorio.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepositorio.deleteById(id);
    }
}
