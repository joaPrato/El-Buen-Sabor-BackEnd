package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rubro_productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class RubroProducto extends Base {

    @NotNull
    private String denominacion;

    private boolean eliminado;

    @ManyToOne
    @JoinColumn(name = "id_rubro_padre")
    private RubroProducto rubroPadre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rubro")
    @Builder.Default
    private List<Producto> producto = new ArrayList<>();

    public void agregarProducto(Producto prod) {
        producto.add(prod);
    }

    public RubroProducto(String denominacion, RubroProducto rubroPadre) {
        this.denominacion = denominacion;
        this.rubroPadre = rubroPadre;
    }

    public void mostrarProductos(){
        for (Producto producto : producto) {
            System.out.println("Denominacion: " + producto.getDenominacion());
        }
    }
}
