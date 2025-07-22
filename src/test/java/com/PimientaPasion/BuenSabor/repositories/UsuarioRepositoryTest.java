package com.PimientaPasion.BuenSabor.repositories;

import com.PimientaPasion.BuenSabor.entities.Usuario;
import com.PimientaPasion.BuenSabor.enums.Rol;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void search() {
        Usuario usuario=Usuario.builder()
                .username("SaraLopez")
                .password("123")
                //.auth0Id("15")
                .rol(Rol.CLIENTE)
                .build();

        List<Usuario> usuarios=new ArrayList<Usuario>();
        usuarios.add(usuario);

        entityManager.persist(usuario);
        entityManager.flush();

        assertEquals(usuarios,usuarioRepository.search("SaraLopez"));

    }

    @Test
    void testSearch() {
        Usuario usuario=Usuario.builder()
                .username("SaraLopez")
                .password("123")
                //.auth0Id("15")
                .rol(Rol.CLIENTE)
                .build();

        List<Usuario> usuarios=new ArrayList<Usuario>();
        usuarios.add(usuario);
        Page<Usuario> usuariosPage = new PageImpl<>(usuarios, PageRequest.of(0, 5), 1);

        entityManager.persist(usuario);
        entityManager.flush();



        assertEquals(usuariosPage,usuarioRepository.search("SaraLopez",PageRequest.of(0,5)));
    }
}