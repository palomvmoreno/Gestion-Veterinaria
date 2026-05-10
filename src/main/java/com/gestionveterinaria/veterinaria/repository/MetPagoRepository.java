package com.gestionveterinaria.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionveterinaria.veterinaria.model.MetPago;

@Repository
public interface MetPagoRepository extends JpaRepository<MetPago, Integer> {

}
