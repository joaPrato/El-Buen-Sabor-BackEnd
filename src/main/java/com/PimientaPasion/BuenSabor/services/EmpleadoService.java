package com.PimientaPasion.BuenSabor.services;


import com.PimientaPasion.BuenSabor.entities.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpleadoService extends BaseService <Empleado,Long> {

    List<Empleado> search(String filtro) throws Exception;

    Page<Empleado> search(String filtro, Pageable pageable) throws Exception;

    Empleado singInEmpleado (String filtro1, String filtro2) throws Exception;

    void eliminarEmpleado (Long empleadoId) throws Exception;

    Empleado modificarEmpleado (Long empleadoId, String nuevoNombre, String nuevoApellido, String nuevoTelefono, String nuevoEmail) throws Exception;

}
