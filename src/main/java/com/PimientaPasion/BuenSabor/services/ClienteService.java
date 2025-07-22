package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.DTOs.ClienteRankingDTO;
import com.PimientaPasion.BuenSabor.entities.Cliente;
import com.PimientaPasion.BuenSabor.entities.Domicilio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;


public interface ClienteService extends BaseService<Cliente,Long>{
    List<Cliente> search(String filtro) throws Exception;

    Page<Cliente> search(String filtro, Pageable pageable) throws Exception;

    Cliente singInCliente (String filtro1, String filtro2) throws Exception;

    void eliminarCliente (Long clienteId) throws Exception;

    Cliente modificarCliente(Long clienteId, String nuevoNombre, String nuevoApellido, String nuevoTelefono, String nuevoEmail) throws Exception;

    List<ClienteRankingDTO> searchMejoresClientes(Date fechaInicio, Date fechaFin) throws Exception;

    List<Domicilio> buscarDomiciliosCliente (String username)throws Exception;

    Cliente buscarCliente(String username) throws Exception;

    Cliente agregarDomicilioCliente(String username,Domicilio domicilioRequest) throws Exception;

}
