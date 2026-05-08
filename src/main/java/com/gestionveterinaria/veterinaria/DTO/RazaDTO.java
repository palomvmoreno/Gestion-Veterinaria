package com.gestionveterinaria.veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class RazaDTO {
    private Integer id;
    private String nombre;
    private String nombreEspecie;
    private List<String> nombresMascotas;

}
