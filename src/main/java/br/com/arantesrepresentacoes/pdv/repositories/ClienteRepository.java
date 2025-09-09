package br.com.arantesrepresentacoes.pdv.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arantesrepresentacoes.pdv.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}