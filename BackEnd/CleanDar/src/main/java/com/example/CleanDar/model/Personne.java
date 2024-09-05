package com.example.CleanDar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne implements UserDetails {  // Ajouter implements UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Personne(String nom, String email, String password, Role role) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Changez cela si vous avez une logique spécifique pour l'expiration des comptes
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Changez cela si vous avez une logique spécifique pour le verrouillage des comptes
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Changez cela si vous avez une logique spécifique pour l'expiration des identifiants
    }

    @Override
    public boolean isEnabled() {
        return true; // Changez cela si vous avez une logique spécifique pour l'activation des comptes
    }
}
