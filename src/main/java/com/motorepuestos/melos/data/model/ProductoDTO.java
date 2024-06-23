package com.motorepuestos.melos.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductoDTO {

    private Long id;
    private String codigo;
    private String descripcion;
    private double precioCosto;
    private double precioVenta;
    private Long categoriaId;
    private Long marcaId;
    private Long tipoId;
    private int stock;


}
