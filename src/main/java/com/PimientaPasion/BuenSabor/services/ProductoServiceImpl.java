package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Producto;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long> implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public List<Producto> searchDisponibles() throws Exception {
        try{
            List<Producto> productos=productoRepository.searchDisponibles();
            return productos;
        }
        catch (Exception e){
            throw new Exception (e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Producto> searchByDenominacion(String filtro) throws Exception {
        try {
            List<Producto> productos = productoRepository.searchByDenominacion(filtro);
            return productos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Producto> searchByDenominacion(String filtro, Pageable pageable) throws Exception {
        try {
            Page<Producto> productos = productoRepository.searchByDenominacion(filtro, pageable);
            return productos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public Page<Producto> searchByPrecioVentaRange(Double precioMinimo, Double precioMaximo, Pageable pageable) throws Exception {
        try {

            Page<Producto> productos = productoRepository.searchByPrecioVentaRange(precioMinimo, precioMaximo, pageable);

            return productos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
