package com.gestionveterinaria.veterinaria.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaInsumoDTO {
    private Integer id;
    private String nombreCategoria;
}
