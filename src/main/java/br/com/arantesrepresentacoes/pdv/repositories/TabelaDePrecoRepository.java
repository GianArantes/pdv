package br.com.arantesrepresentacoes.pdv.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arantesrepresentacoes.pdv.entities.TabelaDePreco;

public interface TabelaDePrecoRepository extends JpaRepository<TabelaDePreco, UUID> {

}
