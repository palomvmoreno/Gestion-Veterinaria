package com.gestionveterinaria.veterinaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "categoria_insumos")
public class CategoriaInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de la categoria debe ser obligatoria")
    @Size(min = 3, max = 20, message = "La categoria debe tener entre 3 y 20 caracteres")
    @Column(name = "nombreCategoria", nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "categoriaInsumo")
    private List<Insumo> insumos;

}