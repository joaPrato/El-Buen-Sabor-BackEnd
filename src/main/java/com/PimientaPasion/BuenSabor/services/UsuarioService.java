package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Long> {
    List<Usuario> search(String filtro) throws Exception;

    Page<Usuario> search(String filtro, Pageable pageable) throws Exception;
}
