package com.gestionveterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.Veterinarios;

@Repository
public interface VeterinariosRepository extends JpaRepository<Veterinarios, Integer> {
}
