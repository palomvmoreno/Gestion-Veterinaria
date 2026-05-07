package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.CatInsumoDTO;
import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;
import com.gestionveterinaria.veterinaria.repository.CatInsumoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CatInsumoService {
    @Autowired
    private CatInsumoRepository categoriaRepository;

    public List<CatInsumoDTO> obtenerTodas(){
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

    private CatInsumoDTO toDTO(CategoriaInsumo CI){
        CatInsumoDTO catInsDTO = new CatInsumoDTO(CI.getId(), CI.getCategoria());
        return catInsDTO;
    }
}
