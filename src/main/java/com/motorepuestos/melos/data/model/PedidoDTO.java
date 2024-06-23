package com.motorepuestos.melos.data.model;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
public class PedidoDTO {

    private Long id;
    private Date fechaIngreso;
    private String proveedor;
    private double costoPedido;
    private List<PedidoProductoModel> pedidoProductos;

}
