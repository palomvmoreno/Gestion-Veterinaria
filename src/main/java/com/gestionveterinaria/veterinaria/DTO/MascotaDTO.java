package com.gestionveterinaria.veterinaria.DTO;


import lombok.Data;

@Data
public class MascotaDTO {
    private Integer id;
    private String nombre;
    private Integer edad;
    private String nombreDuenio;
    private String nombreRaza;

}
