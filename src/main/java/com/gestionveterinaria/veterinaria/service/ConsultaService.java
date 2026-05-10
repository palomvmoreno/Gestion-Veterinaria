package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.ConsultaDTO;
import com.gestionveterinaria.veterinaria.model.Consulta;
import com.gestionveterinaria.veterinaria.repository.ConsultaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<ConsultaDTO> obtenerTodas() {
        return consultaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ConsultaDTO buscarPorId(Integer id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La consulta no existe."));
        return convertirADTO(consulta);
    }

    public Consulta guardar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public String eliminar(Integer id) {
        try {
            Consulta consulta = consultaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No existe la consulta con ID " + id));
            consultaRepository.delete(consulta);
            return "Consulta eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private ConsultaDTO convertirADTO(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(consulta.getId());
        dto.setDetalle(consulta.getDetalle());

        if (consulta.getClinica() != null) {
            dto.setNombreClinica(consulta.getClinica().getNombre());
        } else {
            dto.setNombreClinica("Sin clinica");
        }

        if (consulta.getVeterinario() != null) {
            dto.setNombreVeterinario(consulta.getVeterinario().getNombre() + " " + consulta.getVeterinario().getApellido());
        } else {
            dto.setNombreVeterinario("Sin veterinario");
        }

        return dto;
    }

}
