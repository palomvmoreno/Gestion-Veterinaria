package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.MascotaDTO;
import com.gestionveterinaria.veterinaria.model.Mascota;
import com.gestionveterinaria.veterinaria.repository.MascotaRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<MascotaDTO> listarMascotas(){
        return mascotaRepository.findAll().stream()
        .map(this::convertirADTO).toList();
    }
    public MascotaDTO buscarPorId(Integer id) {
        Mascota mascota = mascotaRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        return convertirADTO(mascota);
    }

    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizar(Integer id, Mascota mascotaDetalles) {
        return mascotaRepository.findById(id).map(mascota -> {
            mascota.setNombre(mascotaDetalles.getNombre());

            mascota.setEdad(mascotaDetalles.getEdad());

            mascota.setDuenio(mascotaDetalles.getDuenio());

            mascota.setRaza(mascotaDetalles.getRaza());
            return mascotaRepository.save(mascota);
        }).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public String eliminar(Integer id) {
        try {
            Mascota mascota = mascotaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
            mascotaRepository.delete(mascota);
            return "La mascota '" + mascota.getNombre() + "' ha sido eliminada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }   



    private MascotaDTO convertirADTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEdad(mascota.getEdad());

        if (mascota.getDuenio() != null) {
            dto.setNombreDuenio(mascota.getDuenio().getNombre() + " " + mascota.getDuenio().getApellido());
        } else {
            dto.setNombreDuenio("Sin dueño registrado");
        }
        
        if (mascota.getRaza() != null) {
            dto.setNombreRaza(mascota.getRaza().getNombre());
        } else {
            dto.setNombreRaza("Mestiza / Desconocida");
        }
        return dto;
    }

}
