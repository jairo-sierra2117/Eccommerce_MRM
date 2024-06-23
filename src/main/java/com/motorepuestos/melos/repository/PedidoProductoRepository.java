package com.motorepuestos.melos.repository;

import com.motorepuestos.melos.data.entity.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {
}

