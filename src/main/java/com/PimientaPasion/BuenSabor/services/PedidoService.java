package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.DTOs.VerPedidosClientedtos.FacturaVerFacturaDTO;
import com.PimientaPasion.BuenSabor.DTOs.VerPedidosClientedtos.PedidoVerDetalleDTO;
import com.PimientaPasion.BuenSabor.DTOs.VerPedidosClientedtos.PedidoVerPedidoDTO;
import com.PimientaPasion.BuenSabor.DTOs.VerPedidosDeliverydtos.PedidoPedidosDeliveryDTO;
import com.PimientaPasion.BuenSabor.entities.Pedido;
import com.PimientaPasion.BuenSabor.enums.EstadoPedido;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService extends BaseService<Pedido,Long> {

    List<PedidoVerPedidoDTO> buscarPedidosCliente(Long id, Pageable pageable)throws Exception;
    PedidoVerDetalleDTO verDetallePedido(Long id)throws Exception;
    FacturaVerFacturaDTO verFacturaPedido(Long id) throws Exception;
    List<PedidoPedidosDeliveryDTO> buscarPedidoPorEstado(EstadoPedido pedido_estado)throws Exception;

}
