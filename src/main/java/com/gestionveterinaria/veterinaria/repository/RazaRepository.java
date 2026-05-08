package com.gestionveterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.Raza;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer> {

}
