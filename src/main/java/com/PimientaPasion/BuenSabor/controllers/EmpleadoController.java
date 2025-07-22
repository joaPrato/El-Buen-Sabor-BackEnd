package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.Empleado;
import com.PimientaPasion.BuenSabor.services.EmpleadoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/empleados")
public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoServiceImpl> {

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
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro,pageable));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }

    @GetMapping("/singInEmpleado")
    public ResponseEntity<?> singInEmpleado(@RequestParam String filtro1,@RequestParam String filtro2){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.singInEmpleado(filtro1,filtro2));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente mas tarde\"}");
        }
    }
    @PutMapping ("/eliminarEmpleado")
    public ResponseEntity<?> eliminarEmpleado(@RequestParam Long empleadoId) {
        try {
            servicio.eliminarEmpleado(empleadoId);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Empleado eliminado correctamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error al eliminar el empleado\"}");
        }
    }

    @PutMapping("/modificarEmpleado")
    public ResponseEntity<?> modificarEmpleado(@RequestBody Empleado empleadoRequest) {
        try {
            Empleado empleadoModificado = servicio.modificarEmpleado(
                    empleadoRequest.getId(),
                    empleadoRequest.getNombre(),
                    empleadoRequest.getApellido(),
                    empleadoRequest.getTelefono(),
                    empleadoRequest.getEmail()
            );

            if (empleadoModificado != null) {
                return ResponseEntity.status(HttpStatus.OK).body(empleadoModificado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Cliente no encontrado\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al modificar el cliente\"}");
        }
    }

}
