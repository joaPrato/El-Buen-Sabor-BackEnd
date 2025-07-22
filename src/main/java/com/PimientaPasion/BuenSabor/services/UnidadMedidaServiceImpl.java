package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.UnidadMedida;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UnidadMedidaServiceImpl extends BaseServiceImpl<UnidadMedida, Long> implements UnidadMedidaService{
   @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Autowired
    public UnidadMedidaServiceImpl(BaseRepository<UnidadMedida, Long> baseRepository) {
        super(baseRepository);
    }
}
