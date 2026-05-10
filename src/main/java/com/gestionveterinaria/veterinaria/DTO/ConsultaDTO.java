package com.gestionveterinaria.veterinaria.DTO;

import lombok.Data;

@Data
public class ConsultaDTO {
    private Integer id;
    private String detalle;
    private String nombreClinica;
    private String nombreVeterinario;
    private String nombreMascota;
    private String nombreMetodoPago;

}
