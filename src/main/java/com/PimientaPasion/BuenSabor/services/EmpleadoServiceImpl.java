package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Empleado;
import com.PimientaPasion.BuenSabor.repositories.BaseRepository;

import com.PimientaPasion.BuenSabor.repositories.EmpleadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado,Long> implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(BaseRepository<Empleado, Long> baseRepository, EmpleadoRepository empleadoRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public List<Empleado> search(String filtro) throws Exception {
        try{
            List<Empleado> empleados = empleadoRepository.search(filtro);
            return empleados;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Page<Empleado> search(String filtro, Pageable pageable) throws Exception {
        try{
            Page<Empleado> empleados= empleadoRepository.search(filtro,pageable);
            return empleados;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Empleado singInEmpleado(String filtro1, String filtro2) throws Exception {
        try {
            Empleado empleado=empleadoRepository.singInEmpleado(filtro1,filtro2);
            return empleado;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarEmpleado(Long empleadoId) throws Exception {
        try {
            if (empleadoId != null) {
                // Llama al método de repositorio para dar de baja al empleado por su ID
                empleadoRepository.eliminarEmpleado(empleadoId);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Empleado modificarEmpleado(Long empleadoId, String nuevoNombre, String nuevoApellido, String nuevoTelefono, String nuevoEmail) throws Exception {
        try {
            if (empleadoId != null) {
                // Obtener el cliente actual
                Empleado empleado = empleadoRepository.findById(empleadoId).orElse(null);

                // Verificar si el cliente existe
                if (empleado != null) {
                    // Actualizar solo los campos que no son nulos
                    if (nuevoNombre != null) {
                        empleado.setNombre(nuevoNombre);
                    }
                    if (nuevoApellido != null) {
                        empleado.setApellido(nuevoApellido);
                    }
                    if (nuevoTelefono != null) {
                        empleado.setTelefono(nuevoTelefono);
                    }
                    if (nuevoEmail != null) {
                        empleado.setEmail(nuevoEmail);
                    }

                    // Guardar los cambios en el repositorio
                    empleadoRepository.save(empleado);

                    return empleado;
                } else {
                    // Empleado no encontrado
                    return null;
                }
            } else {
                // ID de empleado nulo
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Error al modificar el empleado: " + e.getMessage());
        }
    }
}
