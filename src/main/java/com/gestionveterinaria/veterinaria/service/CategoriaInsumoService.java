package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.CategoriaInsumoDTO;
import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;
import com.gestionveterinaria.veterinaria.repository.CategoriaInsumoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaInsumoService {
    @Autowired
    private CategoriaInsumoRepository categoriaRepository;

    public List<CategoriaInsumoDTO> obtenerTodas(){
        return categoriaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public String eliminar(Integer id) {
        try {
            CategoriaInsumo CI = categoriaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se pudo eliminar la categoria con ID " + id + "."));
            categoriaRepository.delete(CI);
            return "La categoria '" + CI.getCategoria() + "Se eliminó exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public CategoriaInsumo guardarCategoria(CategoriaInsumo CI){
        return categoriaRepository.save(CI);
    }

    private CategoriaInsumoDTO toDTO(CategoriaInsumo CI){
        CategoriaInsumoDTO catInsDTO = new CategoriaInsumoDTO(CI.getId(), CI.getCategoria());
        return catInsDTO;
    }
}
