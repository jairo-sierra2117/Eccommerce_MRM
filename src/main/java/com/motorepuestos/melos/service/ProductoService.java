package com.motorepuestos.melos.service;

import com.motorepuestos.melos.data.model.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> getAllProductos();
    ProductoDTO getProductoById(Long id);
    ProductoDTO createProducto(ProductoDTO productoDTO);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);
    void deleteProducto(Long id);
    void actualizarStock(Long productoId, int cantidad);

}
