package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.RubroIngrediente;
import com.PimientaPasion.BuenSabor.entities.RubroProducto;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.RubroIngredienteRepository;
import com.PimientaPasion.BuenSabor.repositories.RubroProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroIngredienteServiceImpl extends BaseServiceImpl<RubroIngrediente,Long>  implements RubroIngredienteService {

    @Autowired
    private RubroIngredienteRepository rubroIngredienteRepository;

    public RubroIngredienteServiceImpl(BaseRepository<RubroIngrediente, Long> baseRepository, RubroIngredienteRepository rubroIngredienteRepository) {
        super(baseRepository);
        this.rubroIngredienteRepository = rubroIngredienteRepository;
    }
    @Override
    public List<RubroIngrediente> searchRubrosIngDisponibles() throws Exception {
        try {
            List<RubroIngrediente> rubroIngredientes=rubroIngredienteRepository.searchRubrosIngDisponibles();
            return rubroIngredientes;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
