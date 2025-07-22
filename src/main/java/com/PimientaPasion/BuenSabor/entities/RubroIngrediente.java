package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rubro_ingrediente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class RubroIngrediente extends Base {

    @NotNull
    private String denominacion;

    private boolean eliminado;

    @ManyToOne
    @JoinColumn(name = "id_rubro_padre")
    private RubroIngrediente rubroPadre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ingrediente")
    @Builder.Default
    private List<Ingrediente> ingrediente = new ArrayList<>();

    public RubroIngrediente(String denominacion, RubroIngrediente rubroPadre) {
        this.denominacion = denominacion;
        this.rubroPadre = rubroPadre;
    }

    public void agregarIngrediente(Ingrediente ingred) {

        ingrediente.add(ingred);
    }

    public void mostrarIngredientes(){
        for (Ingrediente ingrediente : ingrediente) {
            System.out.println("Denominacion: " + ingrediente.getDenominacion());
        }
    }
}
