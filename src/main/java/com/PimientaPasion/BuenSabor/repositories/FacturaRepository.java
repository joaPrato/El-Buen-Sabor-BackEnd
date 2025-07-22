package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Factura;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FacturaRepository extends BaseRepository<Factura,Long> {

    //HU #27
    @Query("SELECT SUM(f.totalVenta) FROM Factura f WHERE f.fechaFacturacion BETWEEN :fechaInicio AND :fechaFin")
    Double calcularTotalIngresos(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query("SELECT SUM(f.totalCosto) FROM Factura f WHERE f.fechaFacturacion BETWEEN :fechaInicio AND :fechaFin")
    Double calcularTotalCostos(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query("SELECT SUM(f.totalVenta - f.totalCosto) FROM Factura f WHERE f.fechaFacturacion BETWEEN :fechaInicio AND :fechaFin")
    Double calcularTotalGanancias(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}
