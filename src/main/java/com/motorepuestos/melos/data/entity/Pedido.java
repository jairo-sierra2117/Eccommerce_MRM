package com.motorepuestos.melos.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @JoinColumn(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;

    @JoinColumn(name = "proveedor", nullable = false)
    private String proveedor;

    @JoinColumn(name = "costo_pedido", nullable = false)
    private double costoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProducto> pedidoProductos;

    // Getters and Setters
}