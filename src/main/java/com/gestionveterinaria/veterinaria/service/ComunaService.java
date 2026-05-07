package com.gestionveterinaria.veterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.ComunaDTO;
import com.gestionveterinaria.veterinaria.model.Clinica;
import com.gestionveterinaria.veterinaria.model.Comuna;
import com.gestionveterinaria.veterinaria.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodas() {
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La comuna no existe."));
        return convertirADTO(comuna);
    }

    public Comuna guardar(Comuna comuna) {
        return comunaRepository.save(comuna);
    }

    public String eliminar(Integer id) {
        try {
            Comuna comuna = comunaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se puede eliminar La comuna con ID " + id + " no existe."));
            comunaRepository.delete(comuna);
            return "La comuna '" + comuna.getNombre() + "Eliminado Exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setId(comuna.getId());
        dto.setNombre(comuna.getNombre());
        
        if (comuna.getRegion() != null) {
            dto.setNombreRegion(comuna.getRegion().getNombre());
        } else {
            dto.setNombreRegion("Sin región asignada");
        }

        if (comuna.getClinicas() != null) {
            dto.setNombresClinicas(comuna.getClinicas().stream()
                    .map(Clinica::getNombre)
                    .toList());
        } else {
            dto.setNombresClinicas(new ArrayList<>());
        }

        return dto;
    }
}
  


