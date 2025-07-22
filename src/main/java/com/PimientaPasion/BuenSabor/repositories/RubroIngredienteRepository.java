package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.RubroIngrediente;
import com.PimientaPasion.BuenSabor.entities.RubroProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroIngredienteRepository extends BaseRepository<RubroIngrediente, Long> {

    @Query("select ri from RubroIngrediente ri where ri.eliminado=false OR ri.eliminado=null")
    List<RubroIngrediente> searchRubrosIngDisponibles();
}

