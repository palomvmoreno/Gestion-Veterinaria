package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.ClinicaDTO;
import com.gestionveterinaria.veterinaria.model.Clinica;
import com.gestionveterinaria.veterinaria.repository.ClinicaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;

    public List<ClinicaDTO> obtenerTodas() {
        return clinicaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ClinicaDTO buscarPorId(Integer id) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La clínica no existe."));
        return convertirADTO(clinica);
    }

    public Clinica guardar(Clinica clinica) {
        return clinicaRepository.save(clinica);
    }

    public String eliminar(Integer id) {
        try {
            Clinica clinica = clinicaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se puede eliminar! La clínica con ID " + id + " no existe."));
            clinicaRepository.delete(clinica);
            return "La clínica '" + clinica.getNombre() + "' ha sido cerrada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private ClinicaDTO convertirADTO(Clinica clinica) {
        ClinicaDTO dto = new ClinicaDTO();
        dto.setId(clinica.getId());
        dto.setNombre(clinica.getNombre());
        dto.setDireccion(clinica.getDireccion());

        if (clinica.getComuna() != null) {
            dto.setNombreComuna(clinica.getComuna().getNombre());
        } else {
            dto.setNombreComuna("   No tiene comuna asignada   ");
        }

        return dto;
    }
    
}
