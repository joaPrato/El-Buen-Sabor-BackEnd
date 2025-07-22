package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {

    //Query para traer los producto disponiles
    @Query("select p from Producto  p where p.eliminado=false OR p.eliminado=null")
    List<Producto> searchDisponibles ();

    @Query("SELECT p FROM Producto p WHERE p.denominacion like %:filtro%")
    List<Producto> searchByDenominacion(@Param("filtro") String filtro);

    //Query para buscar los producto segun denominacion
    @Query(
            value = "SELECT * FROM producto WHERE producto.denominacion LIKE %:filtro%",
            nativeQuery = true
    )
    Page<Producto> searchByDenominacion(@Param("filtro") String filtro, Pageable pageable);


    //query buscar por rango de precio de venta
    @Query(
            value = "SELECT * FROM producto " +
                    "WHERE producto.precio_venta BETWEEN :precioMinimo AND :precioMaximo",
            nativeQuery = true
    )
    Page<Producto> searchByPrecioVentaRange(@Param("precioMinimo") Double precioMinimo,
                                            @Param("precioMaximo") Double precioMaximo,
                                            Pageable pageable);




}
