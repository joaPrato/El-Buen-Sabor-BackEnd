package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Ingrediente;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;
import com.PimientaPasion.BuenSabor.repositories.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IngredienteServiceImpl extends BaseServiceImpl<Ingrediente,Long> implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public IngredienteServiceImpl(BaseRepository<Ingrediente, Long> baseRepository, IngredienteRepository ingredienteRepository) {
        super(baseRepository);
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    @Transactional
    public List<Ingrediente> search(String filtro) throws Exception {
        try{
            List<Ingrediente> ingredientes = ingredienteRepository.searchNativo(filtro);
            return ingredientes;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Page<Ingrediente> search(String filtro, Pageable pageable) throws Exception {
        try{
            Page<Ingrediente> ingredientes= ingredienteRepository.searchNativo(filtro, pageable);
            return ingredientes;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Ingrediente> controlStockIngredientes()throws Exception{
        try{
            List<Ingrediente> ingredientes=ingredienteRepository.controlStockIngredientes();
            return ingredientes;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
