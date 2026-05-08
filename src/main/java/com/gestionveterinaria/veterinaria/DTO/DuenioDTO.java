package com.gestionveterinaria.veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class DuenioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private List<String> nombresMascotas;

}
