package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Cliente extends Base {

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private boolean eliminado;


    //cascade all hace que lasoperaciones hechas sobre la entidad principal también se aplican a la entidad relacionada
    // si actualizo o borro o persisto a persona los relacionados tambien se cambian
    // tambien puedo poner sino cascadeType.operacion y solo con esa operacion se hara la cascada
    // orphanRemoval: remover al huerfano, cuando el usuario en este caso no este relacinado a ninguna entidad se elimina automaticamente
    // es decir si digo setUsuario = null , el usuario se elimina
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //Relación OneToMany ClientePedidosDeliveyDTO y Domicilio
     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
     @JoinColumn(name = "fk_cliente")
     @Builder.Default
     private List<Domicilio> domicilios = new ArrayList<>();

    //-----------------------------------------------------------------------------------
    //Métodos

    public void agregarDomicilio(Domicilio dom){

        domicilios.add(dom);
    }


    //NOSE LA VERDAD
    public void mostrarDomicilios() {
        System.out.println("-----------------------------------------------------------\n");
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println("Calle: " + domicilio.getCalle());
            System.out.println("Número: " + domicilio.getNumeroDomicilio());
            System.out.println("Código Postal:" + domicilio.getCodigoPostal());
            System.out.println("Localidad: " + domicilio.getLocalidad());
            System.out.println("Número Departamento: " + domicilio.getNumeroDpto());
            System.out.println("Piso Departamento: " + domicilio.getNumeroDpto());
        }
    }



}
