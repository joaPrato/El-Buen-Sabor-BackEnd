package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Producto;
import com.PimientaPasion.BuenSabor.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductoServiceImplTest {
    @MockBean
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoServiceImpl productoService;

    @Test
    void searchByDenominacion() throws Exception{
        Producto producto=Producto.builder()
                .denominacion("Hamburgesa")
                .build();

        List<Producto> productos=new ArrayList<Producto>();
        productos.add(producto);

        when(productoRepository.searchByDenominacion("Hamburgesa")).thenReturn(productos);
        assertEquals(productos, productoService.searchByDenominacion("Hamburgesa"));
    }

    @Test
    void testSearchByDenominacion() throws Exception{
        Producto producto=Producto.builder()
                .denominacion("Hamburgesa")
                .build();

        List<Producto> productos=new ArrayList<Producto>();
        productos.add(producto);
        Page<Producto> productosPage = new PageImpl<>(productos, PageRequest.of(0, 5), 1);

        when(productoRepository.searchByDenominacion("Hamburgesa",PageRequest.of(0, 5))).thenReturn(productosPage);
        assertEquals(productosPage,productoService.searchByDenominacion("Hamburgesa",PageRequest.of(0, 5)));
    }

    @Test
    void searchDisponibles() throws Exception{
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

        when(productoRepository.searchDisponibles()).thenReturn(productos);
        assertEquals(productos,productoService.searchDisponibles());

    }
}