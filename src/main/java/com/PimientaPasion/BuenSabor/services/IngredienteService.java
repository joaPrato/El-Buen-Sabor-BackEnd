package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Ingrediente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IngredienteService extends BaseService<Ingrediente, Long>{

    List<Ingrediente> search(String filtro) throws Exception;

    Page<Ingrediente> search(String filtro, Pageable pageable) throws Exception;
    List<Ingrediente> controlStockIngredientes()throws Exception;
}
