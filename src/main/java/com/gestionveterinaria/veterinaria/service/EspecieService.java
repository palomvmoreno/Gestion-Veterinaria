package com.gestionveterinaria.veterinaria.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.EspecieDTO;
import com.gestionveterinaria.veterinaria.model.Especie;
import com.gestionveterinaria.veterinaria.model.Raza;
import com.gestionveterinaria.veterinaria.repository.EspecieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecieService {
    @Autowired
    private EspecieRepository especieRepository;

    public List<EspecieDTO> listarEspecies() {
        return especieRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }
    public EspecieDTO buscarPorId(Integer id) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La especie con ID " + id + " no existe."));
        return convertirADTO(especie);

    }

    public Especie guardar(Especie especie) {
        return especieRepository.save(especie);
    }

   public Especie actualizar(Integer id, Especie especieDetalles) {
        return especieRepository.findById(id).map(especie -> {
            especie.setNombre(especieDetalles.getNombre());
            return especieRepository.save(especie);
        }).orElseThrow(() -> new RuntimeException("La especie con ID " + id + " no existe."));
    }
    public String eliminar(Integer id) {
        try {
            Especie especie = especieRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("La especie con ID " + id + " no existe."));
            especieRepository.delete(especie);
            return "La especie '" + especie.getNombre() + "' ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }


    private EspecieDTO convertirADTO(Especie especie) {
        EspecieDTO dto = new EspecieDTO();
        dto.setId(especie.getId());
        dto.setNombre(especie.getNombre());
        ArrayList<String> listaNombres = new ArrayList<>();

        if (especie.getRazas() != null && !especie.getRazas().isEmpty()) {
            for (Raza raza : especie.getRazas()) {
                listaNombres.add(raza.getNombre());
            }
            dto.setNombresRazas(listaNombres);
        } else {
            listaNombres.add("No tiene razas registradas.");
            dto.setNombresRazas(listaNombres);
        }

        return dto;
    }

    

}
