package com.PimientaPasion.BuenSabor.DTOs.VerPedidosClientedtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//podriamos poner esto y no hace automaticamente las asigancion de atributos geter set contructores ect
// public record Detalle... (Integer cantidad, double subtotal, Producto...) {}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaVerFacturaDTO {
    private Integer cantidad;

    private double subtotal;
    private ProductoVerDatalleDTO productoVerDatalleDTO;

}
