package br.com.arantesrepresentacoes.pdv.entities;

import java.util.UUID;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private UserRole role;

    public Usuario() {
    }

}
