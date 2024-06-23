package com.motorepuestos.melos.service.implementation;


import com.motorepuestos.melos.data.converter.CategoriaConverter;
import com.motorepuestos.melos.data.entity.Categoria;
import com.motorepuestos.melos.data.model.CategoriaDTO;
import com.motorepuestos.melos.repository.CategoriaRepository;
import com.motorepuestos.melos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaConverter categoriaConverter;

    @Override
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaConverter::entityToDto)
                .orElse(null);
    }

    @Override
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaConverter.dtoToEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaConverter.entityToDto(savedCategoria);
    }

    @Override
    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsById(id)) {
            Categoria categoria = categoriaConverter.dtoToEntity(categoriaDTO);
            categoria.setId(id);
            Categoria updatedCategoria = categoriaRepository.save(categoria);
            return categoriaConverter.entityToDto(updatedCategoria);
        }
        return null;
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
