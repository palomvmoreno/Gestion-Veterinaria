package com.gestionveterinaria.veterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.DTO.DuenioDTO;
import com.gestionveterinaria.veterinaria.model.Duenio;
import com.gestionveterinaria.veterinaria.model.Mascota;
import com.gestionveterinaria.veterinaria.repository.DuenioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DuenioService {

    @Autowired
    private DuenioRepository duenioRepository;

    public List<DuenioDTO> obtenerTodos(){
        return duenioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }
    public DuenioDTO buscarPorId(Integer id) {
        Duenio duenio = duenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Duenio no encontrado"));
        return convertirADTO(duenio);
    }

    public String eliminar(Integer id){
        try {
            Duenio duenio = duenioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Duenio no encontrado"));
            duenioRepository.delete(duenio);
            return "El duenio " + duenio.getNombre() + " " + duenio.getApellido() + " ha sido eliminado correctamente.";
            
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Duenio guardar(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    public Duenio actualizar(Integer id, Duenio duenio) {
        Duenio dueno = duenioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Duenio no encontrado"));
        if (duenio.getNombre() != null) {
            duenio.setNombre(duenio.getNombre());
        }
        if (duenio.getApellido() != null) {
            duenio.setApellido(duenio.getApellido());
        }
        if (duenio.getCorreo() != null) {
            duenio.setCorreo(duenio.getCorreo());
        }
        if (duenio.getTelefono() != null) {
            duenio.setTelefono(duenio.getTelefono());
        }
        if (duenio.getDireccion() != null) {
            duenio.setDireccion(duenio.getDireccion());
        }
        return duenioRepository.save(dueno);
    }

    public List<DuenioDTO> buscarPorTelefono(String telefono) {
        return duenioRepository.findByTelefono(telefono).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<DuenioDTO> buscarPorNombreApellido(String nombre, String apellido) {
        return duenioRepository.findByNombreAndApellido(nombre, apellido).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<DuenioDTO> buscarPorNombreMascota(String nombreMascota) {
        return duenioRepository.findByMascotas_NombreIgnoreCase(nombreMascota).stream()
                .map(this::convertirADTO)
                .toList();
    }

    private DuenioDTO convertirADTO(Duenio duenio) {
        DuenioDTO dto = new DuenioDTO();
        dto.setId(duenio.getId());
        dto.setNombre(duenio.getNombre());
        dto.setApellido(duenio.getApellido());
        dto.setCorreo(duenio.getCorreo());
        dto.setTelefono(duenio.getTelefono());
        dto.setDireccion(duenio.getDireccion());

        ArrayList<String> nombresMascotas = new ArrayList<>();

        if (duenio.getMascotas() != null && !duenio.getMascotas().isEmpty()) {
            for (Mascota mascota : duenio.getMascotas()) {
                nombresMascotas.add(mascota.getNombre());
            }
        }
        
        dto.setNombresMascotas(nombresMascotas); 

        return dto;
        
    }
}


