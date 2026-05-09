package com.gestionveterinaria.veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class EspecieDTO {
    private Integer id;
    private String nombre;
    private List<String> nombresRazas;
}
