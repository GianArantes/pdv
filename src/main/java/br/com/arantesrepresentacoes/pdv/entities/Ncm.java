package br.com.arantesrepresentacoes.pdv.entities;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ncm")
public class Ncm {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(unique = true, nullable = false)
    private String codigo;
    private String descricao;

    public Ncm(String codigo) {
        this.codigo = codigo;
    }



}
