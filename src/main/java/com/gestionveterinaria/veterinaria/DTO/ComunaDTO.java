package com.gestionveterinaria.veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ComunaDTO {
    private Integer id;
    private String nombre;
    private String nombreRegion; 
    private List<String> nombresClinicas;

}
