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
    ProductoRepositorio productoRepositorio;
    public List<Producto> listaProductos(){
        return  productoRepositorio.findAll();
    }
    public Optional<Producto>buscarProducto(Long id){
        return productoRepositorio.findById(id);
    }
    public  void guardarProducto(Producto prodcuto){
        productoRepositorio.save(prodcuto);

    }
    public void eliminarProducto(Long id){
        productoRepositorio.deleteById(id);
    }
}
