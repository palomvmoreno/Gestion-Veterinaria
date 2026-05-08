package com.gestionveterinaria.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@Entity
@Table (name = "duenio")
@NoArgsConstructor
@AllArgsConstructor  
public class Duenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del duenio es obligatorio")
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido del duenio es obligatorio")
    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El correo electrónico del duenio es obligatorio")
    @Column(nullable = false, length = 100)
    @Size(min = 5, max = 100, message = "El correo electrónico debe tener entre 5 y 100 caracteres")
    private String correo;

    @NotBlank(message = "El número de teléfono del duenio es obligatorio")
    @Column(nullable = false, length = 20)
    @Size(min = 7, max = 20, message = "El número de teléfono debe tener entre 7 y 20 caracteres")
    private String telefono;

    @NotBlank(message = "La dirección del duenio es obligatoria")
    @Column(nullable = false, length = 200)
    @Size(min = 5, max = 200, message = "La dirección debe tener entre 5 y 200 caracteres")
    private String direccion;

    @OneToMany(mappedBy = "duenio")
    private List<Mascota> mascotas;

}
