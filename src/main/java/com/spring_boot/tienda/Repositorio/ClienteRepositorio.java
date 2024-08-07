package com.spring_boot.tienda.Repositorio;

import com.spring_boot.tienda.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
