package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "detalle_producto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class DetalleProducto extends Base {

    @NotNull
    @Column(name = "cantidad", precision = 10, scale = 2)
    private Integer cantidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;


}
