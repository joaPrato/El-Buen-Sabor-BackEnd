package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService extends BaseService<Producto, Long>{

    List<Producto> searchDisponibles()throws Exception;
    List<Producto> searchByDenominacion( String filtro)throws Exception;
    Page<Producto> searchByDenominacion(String filtro, Pageable pageable) throws Exception;

    Page<Producto> searchByPrecioVentaRange(Double precioMinimo, Double precioMaximo, Pageable pageable) throws Exception;
}
