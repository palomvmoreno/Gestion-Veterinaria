package com.gestionveterinaria.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "mascota")
@NoArgsConstructor
@AllArgsConstructor

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Size(min = 3, max = 70, message = "El nombre debe tener entre 3 y 70 caracteres")
    @Column(nullable = false, length = 70)
    private String nombre;

    @NotNull (message = "La edad de la mascota es obligatoria")
    @Column(nullable = false)
    private Integer edad;

    @ManyToOne()
    @JoinColumn(name = "duenio_id")
    private Duenio duenio;

    @ManyToOne
    @JoinColumn(name = "raza_id")
    private Raza raza;

    @OneToMany(mappedBy = "mascota")
    private java.util.List<Consulta> consultas;

    

}
