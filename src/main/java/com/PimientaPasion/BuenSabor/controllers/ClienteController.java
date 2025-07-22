package com.PimientaPasion.BuenSabor.controllers;


import com.PimientaPasion.BuenSabor.DTOs.ClienteRankingDTO;
import com.PimientaPasion.BuenSabor.entities.Cliente;
import com.PimientaPasion.BuenSabor.entities.Domicilio;
import com.PimientaPasion.BuenSabor.services.ClienteServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path ="api/v1/clientes")
public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl>{

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
    @GetMapping("/searchPaged")
    public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro, pageable));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/singInCliente")
    public ResponseEntity<?> singInCliente(@RequestParam String filtro1, @RequestParam String filtro2){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.singInCliente(filtro1,filtro2));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }


    @PutMapping ("/eliminarCliente")
    public ResponseEntity<?> eliminarCliente(@RequestParam Long clienteId) {
        try {
            servicio.eliminarCliente(clienteId);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Cliente eliminado correctamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al eliminar el cliente\"}");
        }
    }

    @PutMapping("/modificarCliente")
    public ResponseEntity<?> modificarCliente(@RequestBody Cliente clienteRequest) {
        try {
            Cliente clienteModificado = servicio.modificarCliente(
                    clienteRequest.getId(),
                    clienteRequest.getNombre(),
                    clienteRequest.getApellido(),
                    clienteRequest.getTelefono(),
                    clienteRequest.getEmail()
            );

            if (clienteModificado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(clienteModificado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Cliente no encontrado\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al modificar el cliente\"}");
        }
    }


    @GetMapping("/searchMejoresClientes")
    public ResponseEntity<?> searchMejoresClientes(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        try {
            List<ClienteRankingDTO> clientesRanking = servicio.searchMejoresClientes(fechaInicio, fechaFin);
            return ResponseEntity.status(HttpStatus.OK).body(clientesRanking);

        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Invalid date format. Use yyyy-MM-dd\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error no existen pedidos en ese rango\"}");
        }
    }

    @GetMapping("/buscarDomiciliosCliente")
    public  ResponseEntity<?> buscarDomiciliosCliente(@RequestParam String username)throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarDomiciliosCliente(username));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/buscarClente")
    public  ResponseEntity<?> buscarCliente(@RequestParam String username)throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarCliente(username));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }

    @PutMapping("/agregarDomicilioCliente")
    public ResponseEntity<?> agregarDomicilioCliente(@RequestParam String username, @RequestBody Domicilio domicilioRequest) throws  Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.agregarDomicilioCliente(username,domicilioRequest));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }
}
