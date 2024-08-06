package com.spring_boot.tienda.Entidad;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Garantia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer duracion;

    @OneToOne(mappedBy = "garantia")
    private Producto producto;
}
