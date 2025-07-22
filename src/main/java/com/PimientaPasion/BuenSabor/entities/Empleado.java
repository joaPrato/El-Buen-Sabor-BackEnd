package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empleado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Empleado extends Base{
    private String nombre;

    private String apellido;

    private String telefono;

    private int codigoEmpleado;

    private int dni;

    private String email;

    private boolean eliminado;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
