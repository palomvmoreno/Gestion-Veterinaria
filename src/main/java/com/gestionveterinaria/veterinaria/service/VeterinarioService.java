package com.gestionveterinaria.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionveterinaria.veterinaria.model.Veterinario;
import com.gestionveterinaria.veterinaria.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    public List<Veterinario> obtenerTodos() {
        return veterinarioRepository.findAll();
    }

    public Veterinario buscarPorId(Integer id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" El Veterinario no existe en los registros!"));
    }

    public Veterinario guardar(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public String eliminar(Integer id) {
        try {
            Veterinario v = veterinarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(" No existe y como no existe no se puede eliminar."));
            veterinarioRepository.delete(v);
            return "El veterinario " + v.getNombre() + " ha sido eliminado.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

}
