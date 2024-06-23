package com.motorepuestos.melos.data.converter;

import com.motorepuestos.melos.data.entity.Categoria;
import com.motorepuestos.melos.data.model.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter {
    public CategoriaDTO entityToDto(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setName(categoria.getName());
        return dto;
    }

    public Categoria dtoToEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setName(dto.getName());
        return categoria;
    }
}
