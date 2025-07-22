package com.PimientaPasion.BuenSabor.DTOs.VerPedidosDeliverydtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePedidosDeliveyDTO {
    private String nombre;
    private String apellido;
    private String telefono;
}
