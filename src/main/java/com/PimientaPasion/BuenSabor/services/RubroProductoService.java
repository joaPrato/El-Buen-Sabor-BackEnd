package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.RubroProducto;

import java.util.List;

public interface RubroProductoService extends BaseService<RubroProducto,Long> {

    List<RubroProducto> searchRubrosProdDisponibles() throws Exception;
}
