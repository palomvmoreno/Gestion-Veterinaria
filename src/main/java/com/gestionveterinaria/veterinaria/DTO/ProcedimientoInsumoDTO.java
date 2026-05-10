package com.gestionveterinaria.veterinaria.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcedimientoInsumoDTO {
    private Integer id;

    private Integer insumoId;
    private Integer procedimientoId;
    private String insumoNombre;

    private Integer cantidad;

    @Size(max = 200)
    private String observaciones;
}
