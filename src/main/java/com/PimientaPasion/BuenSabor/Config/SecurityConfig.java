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

import java.util.Arrays;
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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                //Es muy importante el orden en que ponemos los permisos,
                                // porque spring security evalua las reglas de arriba a abajo
                                // cuando la ruta conicide verfica si tiene permiso y luego ya no evalua las demas
                                //poner rutas especificas arriba y las mas generales abajo


                                //Autenticacion
                                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()

                                //Consola H2
                                //.requestMatchers(PathRequest.toH2Console()).permitAll()

                                //Autorizacion de acceso a la url
                                //.requestMatchers(new AntPathRequestMatcher("/api/v1/**")).permitAll()

                                //CLIENTE
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/modificarCliente")).hasAnyRole("CLIENTE","ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/buscarDomiciliosCliente")).hasAnyRole("CLIENTE","ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/buscarCliente")).hasAnyRole("CLIENTE","ADMINISTRADOR")


                                .requestMatchers(new AntPathRequestMatcher("/api/v1/clientes/**")).hasRole("ADMINISTRADOR")

                                //DOMICILIO
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/domicilios/{id}", "PUT")).hasAnyRole("CLIENTE","ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/domicilios/{id}", "DELETE")).hasAnyRole("CLIENTE","ADMINISTRADOR")

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/domicilios/**")).hasRole("ADMINISTRADOR")

                                //EMPLEADOS

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/empleados/**")).hasRole("ADMINISTRADOR")

                                //FACTURAS

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/facturas/**")).hasRole("ADMINISTRADOR")

                                //INGREDIENTES
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/ingredientes/**")).hasAnyRole("COCINERO", "ADMINISTRADOR")

                                //PEDIDOS

                                .requestMatchers(new AntPathRequestMatcher("/api/v1/ingredientes/**")).hasRole("ADMINISTRADOR")

                                //PRODUCTOS
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/productos/**")).hasRole("ADMINISTRADOR")

                                //RUBRO_INGREDIENTES
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroIngredientes/**")).hasAnyRole("COCINERO","ADMINISTRADOR")

                                //RUBRO_PRODUCTOS
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroProductos/searchRubrosProdDisponibles")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroProductos/**")).hasRole("ADMINISTRADOR")

                                //UNIDAD DE MEDIDA
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/UnidadMedida/**")).hasAnyRole("COCINERO", "ADMINISTRADOR")

                                //USUARIOS
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/usuarios/**")).hasRole("ADMINISTRADOR")

                                .anyRequest().authenticated()


                )
                //.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //H2
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir orígenes específicos (más seguro que *)
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));

        // Métodos HTTP permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Permitir credenciales
        configuration.setAllowCredentials(true);

        // Headers expuestos al cliente
        configuration.setExposedHeaders(Arrays.asList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
