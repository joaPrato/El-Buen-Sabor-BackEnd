package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.RubroIngrediente;
import com.PimientaPasion.BuenSabor.entities.RubroProducto;

import java.util.List;

public interface RubroIngredienteService extends BaseService<RubroIngrediente,Long> {

    List<RubroIngrediente> searchRubrosIngDisponibles() throws Exception;
}
