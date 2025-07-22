package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Producto;
import com.PimientaPasion.BuenSabor.entities.RubroProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroProductoRepository extends BaseRepository<RubroProducto, Long> {


    //HU #09 Query para presentar productos en el menu
    @Query("select rp from RubroProducto  rp where rp.eliminado=false OR rp.eliminado=null")
    List<RubroProducto> searchRubrosProdDisponibles();

/*
    //query para buscar por rubroProducto (categorias)
    @Query(value = "SELECT rp FROM RubroProducto rp JOIN rp.producto p WHERE p.denominacion LIKE %:denominacion%")
    Page<Producto> searchByCategoriaNombre(
            @Param("denominacion") String denominacion, Pageable pageable);
*/
}
