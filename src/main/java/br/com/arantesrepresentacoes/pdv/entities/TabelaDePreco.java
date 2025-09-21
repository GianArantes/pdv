package br.com.arantesrepresentacoes.pdv.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tabela_de_preco")
public class TabelaDePreco {

@Id
@GeneratedValue(generator = "UUID")
private UUID id;
private String nome;
private LocalDate dataCriacao;
@OneToMany
private List<Produto> produtos;


}
