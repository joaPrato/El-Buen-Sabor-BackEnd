package com.PimientaPasion.BuenSabor.entities;

import com.PimientaPasion.BuenSabor.enums.EstadoPedido;
import com.PimientaPasion.BuenSabor.enums.FormaPago;
import com.PimientaPasion.BuenSabor.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Pedido extends Base {

    @NotNull
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @NotNull
    @Column(name = "hora_estimada_finalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEstimadaFinalizacion;

    @NotNull
    @Column(name = "total")
    private double totalPedido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    @NotNull
    @Column(name = "tipo_envio")
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    @NotNull
    @Column(name = "forma_pago")
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    private boolean eliminado;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_domicilio_entrega")
    private Domicilio domicilioEntrega;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    //-----------------------------------------------------------------------------------
    //Métodos

    public void agregarDetallePedido(DetallePedido detalle) {

        detallePedidos.add(detalle);
    }
    public void mostrarFactura() {
        System.out.println("-----------------------------------------------------------\n");
        System.out.println("Pedidos id: " + getId());
        if (factura != null) {
            System.out.println("Factura Id: " + factura.getId()  + ", Fecha factura: "  + factura.getFechaFacturacion() +
        ", Descuento: " + factura.getPorcentajeDescuento() + ", Forma de pago: " + factura.getFormaPago() + ", Total Costo: "
                    + factura.getTotalCosto() + ", Total Venta: " + factura.getTotalVenta());
        } else {
            System.out.println("No hay factura asociada a este pedido.");
        }
    }

    public void mostrarDetallePedido() {
        System.out.println("-----------------------------------------------------------\n");
        System.out.println("Pedido id: " + getId());
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("DetallePedido Id: " + detallePedido.getId()  +
                    ", Cantidad: " + detallePedido.getCantidad());
        }


    }

    public void mostrarUsuario(){

    }


}
