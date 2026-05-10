package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.ConsultaDTO;
import com.gestionveterinaria.veterinaria.DTO.ProcedimientoDTO;
import com.gestionveterinaria.veterinaria.model.Consulta;
import com.gestionveterinaria.veterinaria.model.Procedimiento;
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

        if (consulta.getMascota() != null) {
            dto.setNombreMascota(consulta.getMascota().getNombre());
        } else {
            dto.setNombreMascota("Mascota no registrada");
        }

        if (consulta.getMetPago() != null) {
            dto.setNombreMetodoPago(consulta.getMetPago().getNombre());
        } else {
            dto.setNombreMetodoPago("Método de pago no definido");
        }

        if(consulta.getProcedimientos() != null){
            dto.setProcedimientos(
                consulta.getProcedimientos()
                .stream()
                .map(this::procedimientoToDTO)
                .toList()
                );
        }
        return dto;
    }

    private ProcedimientoDTO procedimientoToDTO(Procedimiento procedimiento){
    return ProcedimientoDTO.builder()
        .id(procedimiento.getId())
        .nombre(procedimiento.getNombre())
        .descripcion(procedimiento.getDescripcion())
        .costo(procedimiento.getCosto())
        .requiereHospitalizacion(procedimiento.getRequiereHospitalizacion())
        .build();
    }
}
