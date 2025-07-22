package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.DTOs.VerPedidosClientedtos.*;

import com.PimientaPasion.BuenSabor.DTOs.VerPedidosDeliverydtos.ClientePedidosDeliveyDTO;
import com.PimientaPasion.BuenSabor.DTOs.VerPedidosDeliverydtos.DireccionPedidosDeliveryDTO;
import com.PimientaPasion.BuenSabor.DTOs.VerPedidosDeliverydtos.PedidoPedidosDeliveryDTO;
import com.PimientaPasion.BuenSabor.entities.*;
import com.PimientaPasion.BuenSabor.enums.EstadoPedido;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements PedidoService  {

    @Autowired
    PedidoRepository pedidoRepository;

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public List<PedidoVerPedidoDTO> buscarPedidosCliente(Long id_cliente, Pageable pageable) throws Exception {
        try {
            Page<Pedido> pedidos= pedidoRepository.buscarPedidosCliente(id_cliente,pageable);
            ModelMapper modelMapper = new ModelMapper();
            List<PedidoVerPedidoDTO> pedidoVerPedidoDTOS =new ArrayList<PedidoVerPedidoDTO>();

            for (Pedido pedido:pedidos){
                pedidoVerPedidoDTOS.add(modelMapper.map(pedido, PedidoVerPedidoDTO.class));
            }
            return pedidoVerPedidoDTOS;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PedidoVerDetalleDTO verDetallePedido(Long id) throws Exception {
        try {//si la entidad origen en el mapper no tiene un atributo que si tiene la entidad destino
            // entonces este sera null en la destino.
            // y si tenemos cascadeo o orphanRemoval, ese se eliminara por ejemplo si queda null el domicilio
            Pedido pedido=pedidoRepository.verDetallePedido(id);
            ModelMapper modelMapper=new ModelMapper();
            List<DetallePedidoVerDetalleDTO> detallePedidoVerDetalleDTOS=new ArrayList<DetallePedidoVerDetalleDTO>();
            DetallePedidoVerDetalleDTO detallePedidoVerDetalleDTOAux;
            PedidoVerDetalleDTO pedidoVerDetalleDTO=modelMapper.map(pedido,PedidoVerDetalleDTO.class);
            for (DetallePedido detallePedido:pedido.getDetallePedidos()){
                detallePedidoVerDetalleDTOAux=modelMapper.map(detallePedido,DetallePedidoVerDetalleDTO.class);
                detallePedidoVerDetalleDTOAux.setProductoVerDatalleDTO(modelMapper.map(detallePedido.getProducto(), ProductoVerDatalleDTO.class));
                detallePedidoVerDetalleDTOS.add(detallePedidoVerDetalleDTOAux);
            }
            pedidoVerDetalleDTO.setDetallePedidoVerDetalleDTOS(detallePedidoVerDetalleDTOS);
            return pedidoVerDetalleDTO;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public FacturaVerFacturaDTO verFacturaPedido(Long id) throws Exception {
        try{
            Factura factura=pedidoRepository.verFacturaPedido(id);
            ModelMapper modelMapper=new ModelMapper();
            FacturaVerFacturaDTO facturaVerFacturaDTO=modelMapper.map(factura,FacturaVerFacturaDTO.class);
            List<DetalleFacturaVerFacturaDTO> detalleFacturaVerFacturaDTOS = new ArrayList<DetalleFacturaVerFacturaDTO>();
            DetalleFacturaVerFacturaDTO detalleFacturaVerFacturaDTOAux;
            for (DetalleFactura detalleFactura:factura.getDetalleFactura()){
                detalleFacturaVerFacturaDTOAux=(modelMapper.map(detalleFactura, DetalleFacturaVerFacturaDTO.class));
                detalleFacturaVerFacturaDTOAux.setProductoVerDatalleDTO(modelMapper.map(detalleFactura.getProducto(),ProductoVerDatalleDTO.class));
                detalleFacturaVerFacturaDTOS.add(detalleFacturaVerFacturaDTOAux);
            }
            facturaVerFacturaDTO.setDetalleFacturaVerFacturaDTOS(detalleFacturaVerFacturaDTOS);
            return facturaVerFacturaDTO;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<PedidoPedidosDeliveryDTO> buscarPedidoPorEstado(EstadoPedido pedido_estado) throws Exception {
        try{
            List<Pedido> pedidos=pedidoRepository.buscarPedidoPorEstado(pedido_estado);
            ModelMapper modelMapper = new ModelMapper();
            List<PedidoPedidosDeliveryDTO> pedidoPedidosDeliveryDTOS =new ArrayList<PedidoPedidosDeliveryDTO>();
            for (Pedido pedido:pedidos){
                PedidoPedidosDeliveryDTO pedidoPedidosDeliveryDTO=modelMapper.map(pedido,PedidoPedidosDeliveryDTO.class);
                pedidoPedidosDeliveryDTO.setClientePedidosDeliveyDTO(modelMapper.map(pedido.getCliente(), ClientePedidosDeliveyDTO.class));
                pedidoPedidosDeliveryDTO.setDireccionPedidosDeliveryDTO(modelMapper.map(pedido.getDomicilioEntrega(), DireccionPedidosDeliveryDTO.class));
                pedidoPedidosDeliveryDTOS.add(pedidoPedidosDeliveryDTO);
            }
            return  pedidoPedidosDeliveryDTOS;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
