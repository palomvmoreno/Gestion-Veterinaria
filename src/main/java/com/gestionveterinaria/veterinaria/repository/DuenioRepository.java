package com.gestionveterinaria.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.Duenio;

@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Integer> {
    List<Duenio> findByTelefono(String telefono);

    List<Duenio> findByNombreAndApellido(String nombre, String apellido);

    List<Duenio> findByMascotas_NombreIgnoreCase(String nombreMascota);






}
