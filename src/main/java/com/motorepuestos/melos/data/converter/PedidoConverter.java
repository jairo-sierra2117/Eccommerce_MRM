package com.motorepuestos.melos.data.converter;


import com.motorepuestos.melos.data.entity.Pedido;
import com.motorepuestos.melos.data.entity.PedidoProducto;
import com.motorepuestos.melos.data.model.PedidoDTO;
import com.motorepuestos.melos.data.model.PedidoProductoModel;
import com.motorepuestos.melos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoConverter {

    @Autowired
    private ProductoRepository productoRepository;

    public Pedido modelToEntity(PedidoDTO PedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setFechaIngreso(PedidoDTO.getFechaIngreso());
        pedido.setProveedor(PedidoDTO.getProveedor());
        pedido.setCostoPedido(PedidoDTO.getCostoPedido());
        pedido.setPedidoProductos(PedidoDTO.getPedidoProductos().stream()
                .map(pedidoProductoModel -> {
                    PedidoProducto pedidoProducto = new PedidoProducto();
                    pedidoProducto.setProducto(productoRepository.findById(pedidoProductoModel.getProductoId()).orElse(null));
                    pedidoProducto.setCantidad(pedidoProductoModel.getCantidad());
                    pedidoProducto.setPedido(pedido);
                    return pedidoProducto;
                }).collect(Collectors.toList()));
        return pedido;
    }

    public PedidoDTO entityToModel(Pedido entity) {
        PedidoDTO PedidoDTO = new PedidoDTO();
        PedidoDTO.setId(entity.getId());
        PedidoDTO.setFechaIngreso(entity.getFechaIngreso());
        PedidoDTO.setProveedor(entity.getProveedor());
        PedidoDTO.setCostoPedido(entity.getCostoPedido());
        PedidoDTO.setPedidoProductos(entity.getPedidoProductos().stream()
                .map(pedidoProducto -> {
                    PedidoProductoModel pedidoProductoModel = new PedidoProductoModel();
                    pedidoProductoModel.setProductoId(pedidoProducto.getProducto().getId());
                    pedidoProductoModel.setCantidad(pedidoProducto.getCantidad());
                    return pedidoProductoModel;
                }).collect(Collectors.toList()));
        return PedidoDTO;
    }
}
