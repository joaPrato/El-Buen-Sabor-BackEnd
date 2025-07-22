package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "ingrediente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Ingrediente extends Base {

    @NotNull
    private String denominacion;

    @Column(name = "url_imagen")
    private String urlImagen;

    @NotNull
    @Column(name = "precio_compra")
    private double precioCompra;

    @NotNull
    @Column(name = "stock_actual")
    private double stockActual;

    @NotNull
    @Column(name = "stock_minimo")
    private double stockMinimo;

    private boolean eliminado;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;



}
