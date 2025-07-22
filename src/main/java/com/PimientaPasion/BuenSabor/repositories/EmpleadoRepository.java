package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado, Long> {

    @Query(value = "SELECT e FROM Empleado e WHERE e.nombre LIKE %:filtro% OR e.apellido LIKE %:filtro%")
    List<Empleado> search(@Param("filtro") String filtro);
    @Query(value = "SELECT e FROM Empleado e WHERE e.nombre LIKE %:filtro% OR e.apellido LIKE %:filtro%")
    Page<Empleado> search(@Param("filtro") String filtro, Pageable pageable);


    //Query de inicio de sesion empleado
    @Query(
            value="select e from Empleado e where e.usuario.username like %:filtro1% and e.usuario.password like %:filtro2%")
    Empleado singInEmpleado (@Param("filtro1") String filtro1,@Param("filtro2") String filtro2);

    //Query Eliminar Empleado

    @Query(value = "UPDATE Empleado c SET c.eliminado = true WHERE c.id = :empleadoId")
    void eliminarEmpleado (@Param("empleadoId") Long empleadoId);

    //Query Modificar Empleado

    @Query("UPDATE Empleado e SET e.nombre = :nuevoNombre, e.apellido = :nuevoApellido, e.telefono = :nuevoTelefono, e.email = :nuevoEmail WHERE e.id = :empleadoId")
    void modificarEmpleado(
            @Param("empleadoId") Long empleadoId,
            @Param("nuevoNombre") String nuevoNombre,
            @Param("nuevoApellido") String nuevoApellido,
            @Param("nuevoTelefono") String nuevoTelefono,
            @Param("nuevoEmail") String nuevoEmail
    );


}
