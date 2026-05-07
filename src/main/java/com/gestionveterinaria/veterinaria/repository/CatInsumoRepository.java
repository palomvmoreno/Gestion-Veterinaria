package com.gestionveterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionveterinaria.veterinaria.model.CategoriaInsumo;

public interface CatInsumoRepository extends JpaRepository<CategoriaInsumo, Integer>{

}
