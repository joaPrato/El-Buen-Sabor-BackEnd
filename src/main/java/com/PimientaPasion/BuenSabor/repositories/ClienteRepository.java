package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Cliente;
import com.PimientaPasion.BuenSabor.entities.Domicilio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente, Long> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.nombre LIKE %:filtro% OR c.apellido LIKE %:filtro%")
    List<Cliente> search(@Param("filtro") String filtro);

    @Query(value = "SELECT c FROM Cliente c WHERE c.nombre LIKE %:filtro% OR c.apellido LIKE %:filtro%")
    Page<Cliente> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(
            value = "SELECT * FROM cliente WHERE cliente.nombre OR cliente.apellido LIKE %:filtro%",
            nativeQuery = true
    )
    List<Cliente> searchNativo(@Param("filtro") String filtro);

    @Query(
            value = "SELECT * FROM cliente WHERE cliente.nombre LIKE %:filtro% OR cliente.apellido LIKE %:filtro%",
            countQuery = "SELECT count (*) FROM cliente",
            nativeQuery = true
    )
    Page<Cliente> searchNativo(@Param("filtro") String filtro, Pageable pageable);


    //HU #02 Query de inicio de sesion cliente
    @Query(
            value="select c from Cliente c where c.usuario.username = :filtro1 and c.usuario.password = :filtro2")
    Cliente singInCliente (@Param("filtro1") String filtro1,@Param("filtro2") String filtro2);

    // HU #26 query searchMejoresClientes
    @Query("SELECT c.nombre, c.apellido, COUNT(p.cliente.id) AS cantidadPedidos, SUM(p.totalPedido) AS totalCompras " +
            "FROM Cliente c JOIN Pedido p ON c.id = p.cliente.id " +
            "WHERE p.fechaPedido BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(p.cliente) DESC"

    )
    List<Object[]> searchMejoresClientes(@Param("fechaInicio") Date fechaInicio,
                                         @Param("fechaFin") Date fechaFin);

/*
    //HU #01

    @Query(value = "INSERT INTO cliente (nombre, apellido, direccion, departamento, telefono, email, contrasena, rol) " +
            "SELECT :nombre, :apellido, :direccion, :departamento, :telefono, :email, :contrasena, 'cliente' " +
            "WHERE NOT EXISTS (SELECT 1 FROM cliente WHERE email = :email) " +
            "AND LENGTH(:contrasena) >= 8 " +
            "AND :contrasena LIKE '%[A-Z]%' " +
            "AND :contrasena LIKE '%[a-z]%' " +
            "AND :contrasena LIKE '%[^a-zA-Z0-9]%'")

    Cliente crearCliente(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("direccion") String direccion,
                         @Param("departamento") String departamento, @Param("telefono") String telefono,
                         @Param("email") String email, @Param("contrasena") String contrasena);
*/

    //HU #7
    //Eliminar Cliente
    @Modifying
    @Query(value = "UPDATE Cliente c SET c.eliminado = true WHERE c.id = :clienteId")
    void eliminarCliente (@Param("clienteId") Long clienteId);

    //Modificar Cliente
    @Modifying
    @Query(value = "UPDATE Cliente c SET c.nombre = :nuevoNombre, c.apellido = :nuevoApellido, c.telefono = :nuevoTelefono, c.email = :nuevoEmail WHERE c.id = :clienteId")
    Cliente modificarCliente(
            @Param("clienteId") Long clienteId,
            @Param("nuevoNombre") String nuevoNombre,
            @Param("nuevoApellido") String nuevoApellido,
            @Param("nuevoTelefono") String nuevoTelefono,
            @Param("nuevoEmail") String nuevoEmail
    );

    @Query(value = "select c.domicilios from Cliente  c where c.usuario.username = :username")
    List<Domicilio> buscarDomiciliosCliente (@Param("username")String username);

    @Query(value = "select c from Cliente  c where c.usuario.username = :username")
    Cliente buscarCliente (@Param("username")String username);


}
