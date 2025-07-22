package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.Producto;
import com.PimientaPasion.BuenSabor.services.ProductoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/productos")

public class ProductoController extends BaseControllerImpl<Producto, ProductoServiceImpl> {

    @GetMapping("/searchDisponibles")
    public ResponseEntity<?> searchDisponibles (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchDisponibles());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchByDenominacion")
    public ResponseEntity<?> searchByDenominacion(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByDenominacion(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchByDenominacionPage")
    public ResponseEntity<?> searchByDenominacion(@RequestParam String filtro, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByDenominacion(filtro, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("no hay productos que coincidan con esa búsqueda"));
        }
    }


    @GetMapping("/searchByPrecioVentaRange")
    public ResponseEntity<?> searchByPrecioVentaRange(@RequestParam Double precioMinimo,
                                                      @RequestParam Double precioMaximo,
                                                      Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByPrecioVentaRange(precioMinimo, precioMaximo, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

}
