package com.gestionveterinaria.veterinaria.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Procedimiento")
public class Procedimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del procedimiento es obligatorio")
    @Size(min = 1, max = 40)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 120)
    private String descripcion;

    @NotNull(message = "El costo es obligatorio")
    @Positive(message = "El costo debe ser mayor a 0")
    private Integer costo;

    @NotNull(message = "Debe indicar si requiere hospitalizacion")
    private Boolean requiereHospitalizacion;

    @OneToMany(mappedBy = "procedimiento")
    private List<ProcedimientoInsumo> insumos;
}