package com.gestionveterinaria.veterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.UnidadMedida;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer>{

    @Query("SELECT u.nombre FROM UnidadMedida u WHERE LOWER(u.abreviatura) = LOWER(:abreviatura)")
    Optional<String> obtenerNombrePorAbreviatura(@Param("abreviatura") String abreviatura);

    @Query("SELECT u FROM UnidadMedida u WHERE u.abreviatura = :abreviatura")
Optional<UnidadMedida> buscarPorAbreviatura(@Param("abreviatura") String abreviatura);
}
