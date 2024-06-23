package com.motorepuestos.melos.data.converter;


import com.motorepuestos.melos.data.entity.Producto;
import com.motorepuestos.melos.data.model.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoConverter {

    public ProductoDTO entityToDto(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setCodigo(producto.getCodigo());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecioCosto(producto.getPrecioCosto());
        dto.setPrecioVenta(producto.getPrecioVenta());
        dto.setCategoriaId(producto.getCategoriaId());
        dto.setMarcaId(producto.getMarcaId());
        dto.setTipoId(producto.getTipoId());
        dto.setStock(producto.getStock());
        return dto;
    }

    public Producto dtoToEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setCodigo(dto.getCodigo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioCosto(dto.getPrecioCosto());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setCategoriaId(dto.getCategoriaId());
        producto.setMarcaId(dto.getMarcaId());
        producto.setTipoId(dto.getTipoId());
        producto.setStock(dto.getStock());
        return producto;
    }
}
