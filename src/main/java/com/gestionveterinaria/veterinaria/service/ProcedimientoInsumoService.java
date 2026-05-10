package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.ProcedimientoInsumoDTO;
import com.gestionveterinaria.veterinaria.model.Insumo;
import com.gestionveterinaria.veterinaria.model.Procedimiento;
import com.gestionveterinaria.veterinaria.model.ProcedimientoInsumo;
import com.gestionveterinaria.veterinaria.repository.InsumoRepository;
import com.gestionveterinaria.veterinaria.repository.ProcedimientoInsumoRepository;
import com.gestionveterinaria.veterinaria.repository.ProcedimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProcedimientoInsumoService {
    @Autowired
    private ProcedimientoInsumoRepository procedimientoInsumoRepository;

    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    @Autowired
    private InsumoRepository insumoRepository;

    public List<ProcedimientoInsumoDTO> listarRelaciones(){
        return procedimientoInsumoRepository.findAll()
        .stream()
        .map(this::toDTO)
        .toList();
    }

    public ProcedimientoInsumoDTO buscarPorId(Integer id){
        ProcedimientoInsumo procedimientoInsumo = procedimientoInsumoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar el procedimiento"));

        return toDTO(procedimientoInsumo);
    }

    public ProcedimientoInsumoDTO guardarRelacion(ProcedimientoInsumoDTO ProcedimientoInsumoDTO) {

        Procedimiento procedimiento = procedimientoRepository.findById(ProcedimientoInsumoDTO.getProcedimientoId())
            .orElseThrow(() -> new RuntimeException("No se pudo encontrar el procedimiento"));

        Insumo insumo = insumoRepository.findById(ProcedimientoInsumoDTO.getInsumoId())
            .orElseThrow(() -> new RuntimeException("No se pudo encontrar el insumo"));

        ProcedimientoInsumo relacion = ProcedimientoInsumo.builder()
            .procedimiento(procedimiento)
            .insumo(insumo)
            .cantidad(ProcedimientoInsumoDTO.getCantidad())
            .observaciones(ProcedimientoInsumoDTO.getObservaciones())
            .build();

        return toDTO(procedimientoInsumoRepository.save(relacion));
    }

    public ProcedimientoInsumoDTO actualizarRelacion(Integer id, ProcedimientoInsumoDTO procedimientoInsumoDTO) {

        ProcedimientoInsumo relacion = procedimientoInsumoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar la relacion"));

        Procedimiento procedimiento = procedimientoRepository.findById(procedimientoInsumoDTO.getProcedimientoId())
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar el procedimiento"));

        Insumo insumo = insumoRepository.findById(procedimientoInsumoDTO.getInsumoId())
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar el insumo"));

        relacion.setProcedimiento(procedimiento);
        relacion.setInsumo(insumo);
        relacion.setCantidad(procedimientoInsumoDTO.getCantidad());
        relacion.setObservaciones(procedimientoInsumoDTO.getObservaciones());

        return toDTO(procedimientoInsumoRepository.save(relacion));
    }

    public void eliminarRelacion(Integer id){
        ProcedimientoInsumo relacion = procedimientoInsumoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se pudo encontrar la relacion"));

        procedimientoInsumoRepository.delete(relacion);
    }

    private ProcedimientoInsumoDTO toDTO(ProcedimientoInsumo relacion){
        return ProcedimientoInsumoDTO.builder()
        .id(relacion.getId())

        .procedimientoId(relacion.getProcedimiento().getId())
        .insumoId(relacion.getInsumo().getId())
        .insumoNombre(relacion.getInsumo().getNombre())
        .cantidad(relacion.getCantidad())
        .observaciones(relacion.getObservaciones())
        .build();
    }
}
