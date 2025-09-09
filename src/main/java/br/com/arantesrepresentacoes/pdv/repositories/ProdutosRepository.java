package br.com.arantesrepresentacoes.pdv.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.arantesrepresentacoes.pdv.entities.Produto;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, UUID> {
}
