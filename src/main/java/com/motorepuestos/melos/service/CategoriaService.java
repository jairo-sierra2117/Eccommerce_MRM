package com.motorepuestos.melos.service;

import com.motorepuestos.melos.data.model.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> getAllCategorias();
    CategoriaDTO getCategoriaById(Long id);
    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO);
    void deleteCategoria(Long id);
}
