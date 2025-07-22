package com.PimientaPasion.BuenSabor.DTOs.VerPeidosClientedtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoVerPedidoDTO {
    private Long id;
    private Date fechaPedido;
    private double totalPedido;

}
