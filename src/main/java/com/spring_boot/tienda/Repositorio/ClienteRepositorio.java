package com.spring_boot.tienda.Repositorio;

import com.spring_boot.tienda.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
