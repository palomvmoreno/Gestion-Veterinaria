package com.gestionveterinaria.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
    List<Comuna> findByNombre(String nombre);
    @Query("SELECT c FROM Comuna c WHERE c.region.id = :regionId")
    List<Comuna> buscarPorRegion(@Param("regionId") Integer regionId);
    
}
