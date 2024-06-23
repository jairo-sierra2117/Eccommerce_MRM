package com.motorepuestos.melos.controller;

import com.motorepuestos.melos.data.model.CategoriaDTO;
import com.motorepuestos.melos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.getCategoriaById(id);
        return categoriaDTO != null ? ResponseEntity.ok(categoriaDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getName() == null || categoriaDTO.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        CategoriaDTO createdCategoriaDTO = categoriaService.createCategoria(categoriaDTO);
        return ResponseEntity.ok(createdCategoriaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO updatedCategoriaDTO = categoriaService.updateCategoria(id, categoriaDTO);
        return updatedCategoriaDTO != null ? ResponseEntity.ok(updatedCategoriaDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
