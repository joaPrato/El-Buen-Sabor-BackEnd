package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Producto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductoRepository productoRepository;


    @Test
    void buscarPorDenominacion() {
        Producto producto=Producto.builder()
                        .denominacion("Hamburgesa")
                        .build();

        List<Producto> productos=new ArrayList<Producto>();
        productos.add(producto);

        entityManager.persist(producto);
        entityManager.flush();

        assertEquals(productos,productoRepository.searchByDenominacion("Hamburgesa"));
    }

    @Test
    void testBuscarPorDenominacion() {
        Producto producto=Producto.builder()
                .denominacion("Hamburgesa")
                .build();

        List<Producto> productos=new ArrayList<Producto>();
        productos.add(producto);
        Page<Producto> productosPage = new PageImpl<>(productos, PageRequest.of(0, 5), 1);

        entityManager.persist(producto);
        entityManager.flush();



        assertEquals(productosPage,productoRepository.searchByDenominacion("Hamburgesa",PageRequest.of(0,5)));
    }
    /*
    @Test
    void buscarDisponibles() {
        Producto producto1=Producto.builder()
                .denominacion("Hamburgesa")
                .eliminado(false)
                .build();
        Producto producto2=Producto.builder()
                .denominacion("Lomo")
                .eliminado(true)
                .build();



        List<Producto> productos=new ArrayList<Producto>();
        productos.add(producto1);


        entityManager.persist(producto1);
        entityManager.flush();
        entityManager.persist(producto2);
        entityManager.flush();


        assertEquals(productos,productoRepository.buscarDisponibles());

    }

    */

}