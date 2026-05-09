package com.gestionveterinaria.veterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.RazaDTO;
import com.gestionveterinaria.veterinaria.model.Mascota;
import com.gestionveterinaria.veterinaria.model.Raza;
import com.gestionveterinaria.veterinaria.repository.RazaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RazaService {
    @Autowired
    private RazaRepository razaRepository;

    public List<RazaDTO> listaRazas(){
        return razaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }
    public RazaDTO buscarPorId(Integer id) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La raza con ID " + id + " no existe."));
        return convertirADTO(raza);
    }

    public Raza guardar(Raza raza) {
        return razaRepository.save(raza);
    }

    public Raza actualizar(Integer id, Raza razaDetalles) {
        return razaRepository.findById(id).map(raza -> {
            raza.setNombre(razaDetalles.getNombre());
            raza.setEspecie(razaDetalles.getEspecie());
            return razaRepository.save(raza);
        }).orElseThrow(() -> new RuntimeException("La raza con ID: " + id + " no existe."));
    }

    public String eliminar(Integer id) {
        try {
            Raza raza = razaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("La raza con ID " + id + " no existe."));
            razaRepository.delete(raza);
            return "La raza '" + raza.getNombre() + "' ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }




    private RazaDTO convertirADTO(Raza raza) {
        RazaDTO dto = new RazaDTO();
        dto.setId(raza.getId());
        dto.setNombre(raza.getNombre());


        if (raza.getEspecie() != null) {
            dto.setNombreEspecie(raza.getEspecie().getNombre());
        } else {
            dto.setNombreEspecie("Sin especie asignada");
        }


        ArrayList<String> listaNombresMascotas = new ArrayList<>();

        if (raza.getMascotas() != null && !raza.getMascotas().isEmpty()) {
            for (Mascota mascota : raza.getMascotas()) {
                listaNombresMascotas.add(mascota.getNombre());
            }
            dto.setNombresMascotas(listaNombresMascotas);
        } else {
            listaNombresMascotas.add("No hay mascotas registradas de esta raza.");
            dto.setNombresMascotas(listaNombresMascotas);
        }

        return dto;
    }


}
