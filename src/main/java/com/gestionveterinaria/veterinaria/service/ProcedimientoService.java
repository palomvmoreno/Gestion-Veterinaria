package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.ProcedimientoDTO;
import com.gestionveterinaria.veterinaria.DTO.ProcedimientoInsumoDTO;
import com.gestionveterinaria.veterinaria.model.Procedimiento;
import com.gestionveterinaria.veterinaria.model.ProcedimientoInsumo;
import com.gestionveterinaria.veterinaria.repository.ProcedimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProcedimientoService {
    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    public List<ProcedimientoDTO> listarProcedimientos(){
        return procedimientoRepository.findAll()
        .stream()
        .map(this::toDTO)
        .toList();
    }

    public ProcedimientoDTO buscarPorId(Integer id){
        Procedimiento procedimiento = procedimientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar el procedimiento"));

        return toDTO(procedimiento);
    }

    public ProcedimientoDTO guardarProcedimiento(Procedimiento procedimiento){
        Procedimiento procedimiento2 = procedimientoRepository.save(procedimiento);

        return toDTO(procedimiento2);
    }

    public ProcedimientoDTO actualizarProcedimiento(Integer id, ProcedimientoDTO dto) {

        Procedimiento procedimiento = procedimientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar el procedimiento"));

        procedimiento.setNombre(dto.getNombre());
        procedimiento.setDescripcion(dto.getDescripcion());
        procedimiento.setCosto(dto.getCosto());
        procedimiento.setRequiereHospitalizacion(dto.getRequiereHospitalizacion());

        return toDTO(procedimientoRepository.save(procedimiento));
    }

    public void eliminarProcedimiento(Integer id){
        Procedimiento procedimiento = procedimientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar el procedimiento"));

        procedimientoRepository.delete(procedimiento);
    }

    private ProcedimientoDTO toDTO(Procedimiento procedimiento){
        return ProcedimientoDTO.builder()
        .id(procedimiento.getId())
        .nombre(procedimiento.getNombre())
        .descripcion(procedimiento.getDescripcion())
        .costo(procedimiento.getCosto())
        .requiereHospitalizacion(procedimiento.getRequiereHospitalizacion())
        .insumos(
            procedimiento.getInsumos().stream()
            .map(this::toProcedimientoInsumoDTO)
            .toList()
        )
        .build();
    }

    private ProcedimientoInsumoDTO toProcedimientoInsumoDTO(ProcedimientoInsumo procedimientoInsumo){
        return ProcedimientoInsumoDTO.builder()
        .id(procedimientoInsumo.getId())
        .insumoId(procedimientoInsumo.getInsumo().getId())
        .insumoNombre(procedimientoInsumo.getInsumo().getNombre())
        .cantidad(procedimientoInsumo.getCantidad())
        .observaciones(procedimientoInsumo.getObservaciones())
        .build();
    }
}
