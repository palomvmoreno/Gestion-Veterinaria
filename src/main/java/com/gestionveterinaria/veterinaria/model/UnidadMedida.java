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


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unidades_medida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres") 
    @Column(nullable = false, unique = true)
    private String nombre;

    @NotBlank(message = "La abreviatura es obligatoria")
    @Size(min = 1, max = 10, message = "La abreviatura debe ser entre 1 y 10 caracteres")
    @Column(nullable = false, unique = true)
    private String abreviatura;

    @OneToMany(mappedBy = "unidadMedida")
    private List<Insumo> insumos;
}