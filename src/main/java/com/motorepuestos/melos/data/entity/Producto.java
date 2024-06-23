package com.motorepuestos.melos.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "producto_id")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "preciocosto")
    private double precioCosto;

    @Column(name = "precioventa")
    private double precioVenta;

    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column(name = "marca_id")
    private Long marcaId;

    @Column(name = "tipo_id")
    private Long tipoId;

    @Column(name = "stock")
    private int stock;

}