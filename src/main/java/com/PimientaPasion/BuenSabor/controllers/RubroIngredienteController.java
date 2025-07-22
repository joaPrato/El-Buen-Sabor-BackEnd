package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.RubroIngrediente;
import com.PimientaPasion.BuenSabor.services.RubroIngredienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/rubroIngredientes")
public class RubroIngredienteController extends  BaseControllerImpl<RubroIngrediente, RubroIngredienteServiceImpl> {

    @GetMapping("searchRubrosIngDisponibles")
    public ResponseEntity<?> searchRubrosIngDisponibles (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchRubrosIngDisponibles());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
