package com.motorepuestos.melos.service.implementation;

import com.motorepuestos.melos.data.converter.PedidoConverter;
import com.motorepuestos.melos.data.entity.Pedido;
import com.motorepuestos.melos.data.entity.PedidoProducto;
import com.motorepuestos.melos.data.model.PedidoDTO;
import com.motorepuestos.melos.repository.PedidoRepository;
import com.motorepuestos.melos.service.ProductoService;
import com.motorepuestos.melos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoConverter pedidoConverter;

    @Autowired
    private ProductoService productoService;

    @Override
    public PedidoDTO createPedido(PedidoDTO pedidoModel) {
        Pedido pedido = pedidoConverter.modelToEntity(pedidoModel);
        pedido = pedidoRepository.save(pedido);
        // Actualizar el stock de los productos
        actualizarStockProductos(pedido);
        return pedidoConverter.entityToModel(pedido);
    }

    private void actualizarStockProductos(Pedido pedido) {
        for (PedidoProducto pedidoProducto : pedido.getPedidoProductos()) {
            Long productoId = pedidoProducto.getProducto().getId();
            int cantidad = pedidoProducto.getCantidad();
            productoService.actualizarStock(productoId, cantidad);
        }
    }

    @Override
    public List<PedidoDTO> getAllPedidos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoConverter::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO getPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoConverter::entityToModel)
                .orElse(null);
    }

    @Override
    public PedidoDTO updatePedido(Long id, PedidoDTO PedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.getPedidoProductos().clear();
            pedido.getPedidoProductos().addAll(pedidoConverter.modelToEntity(PedidoDTO).getPedidoProductos());
            pedido.setFechaIngreso(PedidoDTO.getFechaIngreso());
            pedido.setProveedor(PedidoDTO.getProveedor());
            pedido.setCostoPedido(PedidoDTO.getCostoPedido());
            pedido = pedidoRepository.save(pedido);
            return pedidoConverter.entityToModel(pedido);
        }
        return null;
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
