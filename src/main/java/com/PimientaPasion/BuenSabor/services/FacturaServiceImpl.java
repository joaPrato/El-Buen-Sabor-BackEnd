package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Factura;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura,Long> implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository, FacturaRepository facturaRepository) {
        super(baseRepository);
        this.facturaRepository = facturaRepository;
    }

    public Double totalIngresos(Date fechaInicio, Date fechaFin) throws Exception{
        try {
            return facturaRepository.calcularTotalIngresos(fechaInicio, fechaFin);

        } catch (Exception e) {
            // Manejar la excepción o lanzar una excepción personalizada si es necesario
            throw new Exception("Error al calcular el total de ingresos: " + e.getMessage(), e);
        }
    }

    public Double totalCostos(Date fechaInicio, Date fechaFin) throws Exception{
        try {

            return facturaRepository.calcularTotalCostos(fechaInicio, fechaFin);

        } catch (Exception e) {
            // Manejar la excepción o lanzar una excepción personalizada si es necesario
            throw new Exception("Error al calcular el total de costos: " + e.getMessage(), e);
        }
    }

    public Double totalGanancias(Date fechaInicio, Date fechaFin) throws Exception{
        try {

            return facturaRepository.calcularTotalGanancias(fechaInicio, fechaFin);

        } catch (Exception e) {
            // Manejar la excepción o lanzar una excepción personalizada si es necesario
            throw new Exception("Error al calcular el total de ganancias: " + e.getMessage(), e);
        }
    }

}