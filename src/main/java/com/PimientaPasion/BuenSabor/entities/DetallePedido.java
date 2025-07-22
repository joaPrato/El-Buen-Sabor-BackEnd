package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "detalle_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class DetallePedido extends Base {

    @NotNull
    private Integer cantidad;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;


    //-----------------------------------------------------------------------------------
    //Métodos

}
