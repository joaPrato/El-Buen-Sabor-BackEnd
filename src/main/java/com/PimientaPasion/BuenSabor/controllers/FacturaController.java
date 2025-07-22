package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.Factura;
import com.PimientaPasion.BuenSabor.services.FacturaServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path ="api/v1/facturas")
public class FacturaController extends BaseControllerImpl<Factura, FacturaServiceImpl>{

    @GetMapping("/totalIngresos")
    public ResponseEntity<?> totalIngresos(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            Double totalIngresos = servicio.totalIngresos(fechaInicio, fechaFin);
            Double totalCostos = servicio.totalCostos(fechaInicio, fechaFin);
            Double totalGanancias = servicio.totalGanancias(fechaInicio, fechaFin);

            return ResponseEntity.status(HttpStatus.OK).body("El total de los ingresos es de: " + totalIngresos + "\n" +
                    "El total de los costos es de: " + totalCostos + "\n" +
                    "El total de ganancias es de: " + totalGanancias);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al mostrar datos de factura.\"}");

        }
    }

}