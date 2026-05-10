package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.VeterinariosDTO;
import com.gestionveterinaria.veterinaria.model.Veterinarios;
import com.gestionveterinaria.veterinaria.repository.VeterinariosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VeterinariosService {
    @Autowired
    private VeterinariosRepository veterinariosRepository;

    public List<VeterinariosDTO> obtenerTodos() {
        return veterinariosRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public VeterinariosDTO buscarPorId(Integer id) {
        Veterinarios veteri = veterinariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error no existe."));
        return convertirADTO(veteri);
    }

    public Veterinarios guardar(Veterinarios asignacion) {
        return veterinariosRepository.save(asignacion);
    }

    public String eliminar(Integer id) {
        try {
            Veterinarios veteri = veterinariosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Error no se puede eliminar. La asignacion con ID " + id + " no existe."));
            veterinariosRepository.delete(veteri);
            return "Asignacion eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private VeterinariosDTO convertirADTO(Veterinarios v) {
        VeterinariosDTO dto = new VeterinariosDTO();
        dto.setId(v.getId());

        if (v.getVeterinario() != null) {
            dto.setNombreVeterinario(v.getVeterinario().getNombre());
        } else {
            dto.setNombreVeterinario("Sin veterinario asignado");
        }

        if (v.getClinica() != null) {
            dto.setNombreClinica(v.getClinica().getNombre());
        } else {
            dto.setNombreClinica("Sin clinica asignada");
        }

        return dto;
    }

}
