package br.com.arantesrepresentacoes.pdv.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private UserRole role;
    private UserStatus status;

    public Usuario(String nome, String email, String senha, UserRole role, UserStatus status) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
