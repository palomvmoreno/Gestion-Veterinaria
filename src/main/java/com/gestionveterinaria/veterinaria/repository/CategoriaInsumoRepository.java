package com.gestionveterinaria.veterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;

@Repository
public interface CategoriaInsumoRepository extends JpaRepository<CategoriaInsumo, Integer>{

    @Query("SELECT c FROM CategoriaInsumo c WHERE c.categoria = :nombre")
    Optional<CategoriaInsumo> buscarPorNombre(@Param("nombre") String nombre);

}