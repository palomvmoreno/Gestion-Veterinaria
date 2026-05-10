package com.gestionveterinaria.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.Insumo;


@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer>{

    @Query("SELECT i FROM Insumo i WHERE i.categoriaInsumo.categoria = :nombre")
        List<Insumo> buscarPorCategoria(@Param("nombre") String nombre);

    @Query("SELECT i FROM Insumo i WHERE i.stockActual <= i.stockMinimo")
    List<Insumo> findBajoStock();
}
