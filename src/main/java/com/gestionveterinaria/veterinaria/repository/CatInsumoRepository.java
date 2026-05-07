package com.gestionveterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;

@Repository
public interface CatInsumoRepository extends JpaRepository<CategoriaInsumo, Integer>{

}
