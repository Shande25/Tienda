package com.spring_boot.tienda.Repositorio;

import com.spring_boot.tienda.Entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
List<Producto> findByNombreContainingIgnoreCase(String nombre);

}
