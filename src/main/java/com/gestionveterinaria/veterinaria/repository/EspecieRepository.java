package com.gestionveterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer> {
    

}
