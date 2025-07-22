package com.PimientaPasion.BuenSabor.entities;

import com.PimientaPasion.BuenSabor.enums.FormaPago;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Factura extends Base {

    @NotNull
    @Column(name = "fecha_facturacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;

    @Column(name = "mp_payment_id")
    private int numeroFactura;

    @Column(name = "mp_merchant_order_id")
    private Double porcentajeDescuento;

    @NotNull
    private FormaPago formaPago;

    @NotNull
    private Double totalCosto;

    @NotNull
    @Column(name = "total_venta")
    private Double totalVenta;

    private boolean eliminado;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle_factura")
    @Builder.Default
    private List<DetalleFactura> detalleFactura = new ArrayList<>();

    public void agregarDetalleFactura(DetalleFactura detalle) {
        detalleFactura.add(detalle);
    }
}
