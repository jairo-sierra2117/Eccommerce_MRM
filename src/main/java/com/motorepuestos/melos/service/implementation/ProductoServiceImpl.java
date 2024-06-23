package com.motorepuestos.melos.service.implementation;

import com.motorepuestos.melos.data.converter.ProductoConverter;
import com.motorepuestos.melos.data.entity.Producto;
import com.motorepuestos.melos.data.model.ProductoDTO;
import com.motorepuestos.melos.repository.ProductoRepository;
import com.motorepuestos.melos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoConverter productoConverter;

    @Override
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO getProductoById(Long id) {
        return productoRepository.findById(id)
                .map(productoConverter::entityToDto)
                .orElse(null);
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto producto = productoConverter.dtoToEntity(productoDTO);
        Producto savedProducto = productoRepository.save(producto);
        return productoConverter.entityToDto(savedProducto);
    }

    @Override
    public void actualizarStock(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId).orElse(null);
        if (producto != null) {
            int nuevoStock = producto.getStock() + cantidad;
            producto.setStock(nuevoStock);
            productoRepository.save(producto);
        }
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        if (productoRepository.existsById(id)) {
            Producto producto = productoConverter.dtoToEntity(productoDTO);
            producto.setId(id);
            Producto updatedProducto = productoRepository.save(producto);
            return productoConverter.entityToDto(updatedProducto);
        }
        return null;
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
