package com.motorepuestos.melos.service;

import com.motorepuestos.melos.data.model.PedidoDTO;

import java.util.List;

public interface PedidoService {
    PedidoDTO createPedido(PedidoDTO PedidoDTO);
    List<PedidoDTO> getAllPedidos();
    PedidoDTO getPedidoById(Long id);
    PedidoDTO updatePedido(Long id, PedidoDTO PedidoDTO);
    void deletePedido(Long id);
}