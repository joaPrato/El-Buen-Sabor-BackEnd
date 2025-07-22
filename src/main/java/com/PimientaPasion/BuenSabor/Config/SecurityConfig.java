package com.PimientaPasion.BuenSabor.Config;


import com.PimientaPasion.BuenSabor.JWT.JwtAuthenticationFilter;
import com.PimientaPasion.BuenSabor.enums.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest

                                //Autenticacion
                                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                                //Consola H2
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                //Autorizacion de acceso a la url
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).permitAll()

                                // .requestMatchers(new AntPathRequestMatcher("/api/v1/empleados/**")).hasAnyAuthority("ADMINISTRADOR")
                                /*
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/usuarios/**")).hasAnyAuthority("ADMINISTRADOR")

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/modificarCliente")).hasAnyAuthority("CLIENTE")

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/empleados/modificarEmpleado")).hasAnyAuthority("ADMINISTRADOR")

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/empleados/modificarEmpleado")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/empleados/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/empleados")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/productos/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/domicilios/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/buscarPedidosCliente")).hasAnyAuthority("CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/verDetallePedido")).hasAnyAuthority("CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/verFacturaPedido")).hasAnyAuthority("CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/buscarPedidoPorEstado")).hasAnyAuthority("DELIVERY","CAJERO","COCINERO")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/ingredientes/**")).hasAnyAuthority("ADMINISTRADOR","COCINERO")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/productos/buscarPorDenominacion")).hasAnyAuthority("CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/productos/buscarPorDenominacionPage")).hasAnyAuthority("CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/productos/buscarDisponibles")).hasAnyAuthority("CLIENTE")

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroProductos/buscarRubrosProdDisponibles")).hasAnyAuthority("CLIENTE")
                                */

                                .anyRequest().authenticated()


                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //H2
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



}
