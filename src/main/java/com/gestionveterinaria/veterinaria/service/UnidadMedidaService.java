package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.UnidadMedidaDTO;
import com.gestionveterinaria.veterinaria.model.UnidadMedida;
import com.gestionveterinaria.veterinaria.repository.UnidadMedidaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UnidadMedidaService {
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    public List<UnidadMedidaDTO> obtenerUnidadMedida(){
        return unidadMedidaRepository.findAll().stream()
        .map(this::toDTO)
        .toList();
    }

    public UnidadMedidaDTO buscarNombreAbreviatura(String abrev){
        String nombre = unidadMedidaRepository.obtenerNombrePorAbreviatura(abrev)
        .orElseThrow(() -> new RuntimeException("Abreviatura no encontrada"));

        UnidadMedidaDTO dto = new UnidadMedidaDTO();
        dto.setNombre(nombre);
        dto.setAbreviatura(abrev);

        return dto;
    }

    public void eliminarUnimed(Integer id){
        UnidadMedida unidad = unidadMedidaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));

        unidadMedidaRepository.delete(unidad);
    }

    public UnidadMedida saveUnimed(UnidadMedida unimed){
        return unidadMedidaRepository.save(unimed);
    }

    private UnidadMedidaDTO toDTO(UnidadMedida UM){
        UnidadMedidaDTO UMDTO = new UnidadMedidaDTO(UM.getId(), UM.getNombre(), UM.getAbreviatura());
        return UMDTO;
    }
}
