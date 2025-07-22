package com.PimientaPasion.BuenSabor.DTOs.VerPeidosClientedtos;

import com.PimientaPasion.BuenSabor.enums.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaVerFacturaDTO {
    private Date fechaFacturacion;
    private int numeroFactura;
    private Double porcentajeDescuento;
    private FormaPago formaPago;
    private Double totalVenta;
    private boolean eliminado;
    private List<DetalleFacturaVerFacturaDTO> detalleFacturaVerFacturaDTOS;

}
