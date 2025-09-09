package br.com.arantesrepresentacoes.pdv.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nome;
    private Double precoBase;
    private Double peso;
    private Ncm ncm;

}
