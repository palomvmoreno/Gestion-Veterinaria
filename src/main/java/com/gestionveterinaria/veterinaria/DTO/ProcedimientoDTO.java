package com.gestionveterinaria.veterinaria.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcedimientoDTO {

    private Integer id;

    private String nombre;

    private String descripcion;

    private Integer costo;

    private Boolean requiereHospitalizacion;

    private List<ProcedimientoInsumoDTO> insumos;
}
