package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Factura;

import java.util.Date;

public interface FacturaService extends BaseService<Factura,Long> {

    Double totalIngresos(Date fechaInicio, Date fechaFin) throws Exception;

    Double totalCostos(Date fechaInicio, Date fechaFin) throws Exception;

    Double totalGanancias(Date fechaInicio, Date fechaFin) throws Exception;

}