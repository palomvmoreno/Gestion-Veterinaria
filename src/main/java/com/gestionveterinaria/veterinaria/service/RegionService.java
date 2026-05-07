package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.RegionDTO;
import com.gestionveterinaria.veterinaria.model.Region;
import com.gestionveterinaria.veterinaria.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodas() {
        return regionRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Integer id) {
        Region region = regionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        return convertirADTO(region);
    }

    public String eliminar(Integer id) {
        try {
            Region region = regionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se puede eliminar La región con ID " + id + " no existe."));
            regionRepository.delete(region);
            return "La región '" + region.getNombre() + "Se eliminó exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Region guardarRegion(Region region) {
        return regionRepository.save(region);
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setNombre(region.getNombre());
        
        return dto;
    }
    
}
