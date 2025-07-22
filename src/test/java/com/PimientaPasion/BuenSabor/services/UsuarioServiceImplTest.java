package com.PimientaPasion.BuenSabor.services;

import com.PimientaPasion.BuenSabor.entities.Usuario;
import com.PimientaPasion.BuenSabor.enums.Rol;
import com.PimientaPasion.BuenSabor.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class UsuarioServiceImplTest {

    @MockBean
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioServiceImpl usuarioService;
/*
    @Test
    void search() throws Exception{
        Usuario usuario=Usuario.builder()
                .username("SaraLopez")
                .password("123")
                //.auth0Id("15")
                .rol(Rol.CLIENTE)
                .build();

        List<Usuario> usuarios=new ArrayList<Usuario>();
        usuarios.add(usuario);

        when(usuarioRepository.searchNativo("SaraLopez")).thenReturn(usuarios);
        assertEquals(usuarios,usuarioService.search("SaraLopez"));


    }

    @Test
    void testSearch() throws Exception{
        Usuario usuario=Usuario.builder()
                .username("SaraLopez")
                .password("123")
                //.auth0Id("15")
                .rol(Rol.CLIENTE)
                .build();

        List<Usuario> usuarios=new ArrayList<Usuario>();
        usuarios.add(usuario);
        Page<Usuario> usuariosPage = new PageImpl<>(usuarios,PageRequest.of(0, 5) , 1);

        when(usuarioRepository.searchNativo("SaraLopez",PageRequest.of(0, 5))).thenReturn(usuariosPage);
        assertEquals(usuariosPage,usuarioService.search("SaraLopez",PageRequest.of(0, 5)));
    }*/
}