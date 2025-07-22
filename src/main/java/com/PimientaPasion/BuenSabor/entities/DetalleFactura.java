package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "detalle_factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class DetalleFactura extends Base {

    @NotNull
    private Integer cantidad;

    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    //-----------------------------------------------------------------------------------
    //Métodos
    public void agregarProducto(Producto prod){

        this.producto = prod;
    }

}
