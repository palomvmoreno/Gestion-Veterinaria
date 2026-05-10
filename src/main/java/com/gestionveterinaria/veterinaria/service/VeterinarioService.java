package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.VeterinarioDTO;
import com.gestionveterinaria.veterinaria.model.Veterinario;
import com.gestionveterinaria.veterinaria.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<VeterinarioDTO> obtenerTodos() {
        return veterinarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public VeterinarioDTO buscarPorId(Integer id) {
        Veterinario v = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El veterinario no existe."));
        return convertirADTO(v);
    }

    public Veterinario guardar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public String eliminar(Integer id) {
        try {
            Veterinario v = veterinarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No existe el veterinario con ID " + id));
            veterinarioRepository.delete(v);
            return "Veterinario eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private VeterinarioDTO convertirADTO(Veterinario v) {
        VeterinarioDTO dto = new VeterinarioDTO();
        dto.setId(v.getId());
        dto.setNombre(v.getNombre());
        dto.setApellido(v.getApellido());
        dto.setEmail(v.getEmail());
        return dto;
    }

}
