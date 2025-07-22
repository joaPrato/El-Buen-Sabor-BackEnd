package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.RubroProducto;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.RubroProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroProductoServiceImpl extends BaseServiceImpl<RubroProducto,Long>  implements RubroProductoService {

    @Autowired
    private RubroProductoRepository rubroProductoRepository;

    public RubroProductoServiceImpl(BaseRepository<RubroProducto, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<RubroProducto> searchRubrosProdDisponibles() throws Exception {
        try {
            List<RubroProducto> rubroProductos=rubroProductoRepository.searchRubrosProdDisponibles();
            return rubroProductos;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
