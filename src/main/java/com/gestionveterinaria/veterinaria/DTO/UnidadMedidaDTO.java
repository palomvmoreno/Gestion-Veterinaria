package com.gestionveterinaria.veterinaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadMedidaDTO {
    private Integer id;
    private String nombre;
    private String abreviatura;
}
