package com.gestionveterinaria.veterinaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre del insumo es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del insumo debe tener entre 3 y 50 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La descripcion del insumo es obligatoria")
    @Size(max = 100, message = "La descripcion del insumo no debe tener mas de 100 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stockActual;

    @Min(value = 0, message = "El stock minimo no puede ser negativo")
    @Column(nullable = false)
    private Integer stockMinimo;

    @Min(value = 0, message = "El precio de compra no puede ser negativo")
    @Column(nullable = false)
    private Integer precioCompra;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message = "La categoria es obligatoria")
    private CategoriaInsumo categoriaInsumo;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id", nullable = false)
    @NotNull(message = "La unidad de medida es obligatoria")
    private UnidadMedida unidadMedida;

    @OneToMany(mappedBy = "insumo")
    private List<ProcedimientoInsumo> procedimientos;
}
