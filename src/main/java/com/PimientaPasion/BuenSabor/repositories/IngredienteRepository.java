package com.PimientaPasion.BuenSabor.repositories;
import com.PimientaPasion.BuenSabor.entities.Ingrediente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends BaseRepository<Ingrediente, Long> {

    @Query(value = "SELECT i FROM Ingrediente i WHERE i.denominacion LIKE %:filtro%")
    List<Ingrediente> search(@Param("filtro") String filtro);

    @Query(value = "SELECT i FROM Ingrediente i WHERE i.denominacion LIKE %:filtro%")
    Page<Ingrediente> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(
            value = "SELECT * FROM ingrediente WHERE ingrediente.denominacion LIKE %:filtro%",
            nativeQuery = true
    )
    List<Ingrediente> searchNativo(@Param("filtro") String filtro);
    @Query(
            value = "SELECT * FROM ingrediente WHERE ingrediente.denominacion LIKE %:filtro%",
            countQuery = "SELECT count(*) FROM productosInsumo",
            nativeQuery = true
    )
    Page<Ingrediente> searchNativo(@Param("filtro") String filtro, Pageable pageable);

    //HU #24 control de stock
    @Query("select i from Ingrediente i where i.stockActual < 1.2*i.stockMinimo and i.eliminado!=true")
    List<Ingrediente>controlStockIngredientes();

}
