package com.gestionveterinaria.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "metodo_pago")
@NoArgsConstructor
@AllArgsConstructor
public class MetPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El RUT es obligatorio")
    @Column(name = "rut", nullable = false, unique = true)
    @Size(min = 9, max = 12, message = "El RUT debe tener entre 9 y 12 caracteres")
    private String rut;
    
    @NotBlank(message = "El nombre del método de pago es obligatorio")
    @Column(name = "nombre", nullable = false)
    @Size(min = 3, max = 50, message = "El nombre del método de pago debe tener entre 3 y 50 caracteres")
    private String nombre;

    @Column(name = "descripcion")
    @Size(max = 200, message = "La descripción del método de pago no puede exceder los 200 caracteres")
    private String descripcion;

    @OneToMany(mappedBy = "metPago")
    private java.util.List<Consulta> consultas;
    


}
