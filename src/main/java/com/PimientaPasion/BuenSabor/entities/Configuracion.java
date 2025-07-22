package com.PimientaPasion.BuenSabor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "configuracion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Configuracion extends Base{
    private int cantidadCocineros;
    private String emailEmpresa;
    private String tokenMercadoPgo;
}
