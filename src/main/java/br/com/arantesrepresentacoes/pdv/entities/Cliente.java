package br.com.arantesrepresentacoes.pdv.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nomeOficial; //Razão Social ou Nome Completo
    private String nomeAlternativo; //Nome Fantasia ou Nome Social
    private String documento1; //CPF ou CNPJ
    private String documento2; //RG ou IE
    private String email;
    private String telefone;
    @Column(length = 2)
    private String estado;
    private String endereco;
    private ClienteStatus status;
    private LocalDate ultimaVenda;
    @ManyToOne
    @JoinColumn(name = "representante_id")
    private Usuario representante;

}
