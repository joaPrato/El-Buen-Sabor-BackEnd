package com.PimientaPasion.BuenSabor.DTOs.VerPedidosDeliverydtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionPedidosDeliveryDTO {
    private String calle;
    private Integer numeroDomicilio;
    private String localidad;
}
