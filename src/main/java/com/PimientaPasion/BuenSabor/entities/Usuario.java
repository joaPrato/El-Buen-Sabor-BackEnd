package com.PimientaPasion.BuenSabor.entities;

import com.PimientaPasion.BuenSabor.enums.Rol;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Builder
@Data

public class Usuario extends Base implements UserDetails {

    /*@NotNull
    @Column(name = "auth0_id", nullable = false, unique = true)
    private String auth0Id;*/

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @NonNull
    private String password;

    private boolean eliminado;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //que rol tiene mi usuario
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}