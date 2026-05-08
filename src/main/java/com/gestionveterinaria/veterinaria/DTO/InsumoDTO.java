package com.gestionveterinaria.veterinaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoDTO {
    private Integer id;

    private String nombre, descripcion;
    private Integer precioCompra;

    private String categoriaNombre;

    private String unidadMedidaNombre, unidadMedidaAbreviatura;

    private Integer stockActual, stockMinimo;

}
