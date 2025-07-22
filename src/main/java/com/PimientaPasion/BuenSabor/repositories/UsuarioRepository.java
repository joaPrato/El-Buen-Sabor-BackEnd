package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.username LIKE %:filtro%")
    List<Usuario> search(@Param("filtro") String filtro);

    @Query(value = "SELECT u FROM Usuario u WHERE u.username LIKE %:filtro%")
    Page<Usuario> search(@Param("filtro") String filtro, Pageable pageable);

    @Query(
            value = "SELECT * FROM usuario WHERE usuario.username LIKE %:filtro%",
            nativeQuery = true
    )
    List<Usuario> searchNativo(@Param("filtro") String filtro);
    @Query(
            value = "SELECT * FROM usuario WHERE usuario.username LIKE %:filtro%",
            nativeQuery = true
    )
    Page<Usuario> searchNativo(@Param("filtro") String filtro, Pageable pageable);

    Optional<Usuario> findByUsername(String username);


}
