package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.Pedido;
import com.PimientaPasion.BuenSabor.enums.EstadoPedido;
import com.PimientaPasion.BuenSabor.services.PedidoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends  BaseControllerImpl<Pedido, PedidoServiceImpl>{

    @GetMapping("/buscarPedidosCliente")
    public ResponseEntity<?> buscarPedidosCliente(@RequestParam Long id_cliente, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPedidosCliente(id_cliente,pageable));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }


    @GetMapping("/verDetallePedido")
    public ResponseEntity<?> verDetallePedido (@RequestParam Long id_pedido){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.verDetallePedido(id_pedido));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/verFacturaPedido")
    public ResponseEntity<?> verFacturaPedido (@RequestParam Long id_pedido){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.verFacturaPedido(id_pedido));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/buscarPedidoPorEstado")
    public ResponseEntity<?> buscarPedidoPorEstado(@RequestParam EstadoPedido estado_pedido){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPedidoPorEstado(estado_pedido));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

}
