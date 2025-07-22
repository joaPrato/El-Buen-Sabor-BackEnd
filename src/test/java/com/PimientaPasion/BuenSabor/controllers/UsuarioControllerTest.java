package com.PimientaPasion.BuenSabor.controllers;

import com.PimientaPasion.BuenSabor.entities.Usuario;
import com.PimientaPasion.BuenSabor.enums.Rol;
import com.PimientaPasion.BuenSabor.services.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
    /*
    @MockBean
    private UsuarioServiceImpl usuarioService;

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
        when(usuarioService.search("SaraLopez")).thenReturn(usuarios);


    }


    @Test
    void testSearch() throws Exception {
    }

     */
}