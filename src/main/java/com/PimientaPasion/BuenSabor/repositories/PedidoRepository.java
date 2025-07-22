package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Factura;
import com.PimientaPasion.BuenSabor.entities.Pedido;
import com.PimientaPasion.BuenSabor.enums.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {

    //HU#13 ClientePedidosDeliveyDTO Visualiza Pedidos (paginado y con DTO)
    @Query("select p from Pedido p where p.cliente.usuario.username = :usuario_cliente")
    Page <Pedido> buscarPedidosCliente (@Param("usuario_cliente") Long usuario_cliente, Pageable pageable);

    //HU#13 ClientePedidosDeliveyDTO Visualiza el Detalle Un Pedido
    @Query("select p from  Pedido p where p.id=:id_pedido")
    Pedido verDetallePedido (@Param("id_pedido")Long id_pedido);

    //HU#13 ClientePedidosDeliveyDTO Visualiza la factura Un Pedido
    @Query("select p.factura from Pedido  p where p.id= :id_pedido")
    Factura verFacturaPedido(@Param("id_pedido")Long id_pedido);

    //HU #16 Visualizar Pedido en cierto estado
    @Query("select p from Pedido  p where p.estadoPedido =:estado_pedido")
    List<Pedido> buscarPedidoPorEstado (@Param("estado_pedido")EstadoPedido estado_Pedido);



}