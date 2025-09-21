package br.com.arantesrepresentacoes.pdv.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arantesrepresentacoes.pdv.entities.Ncm;
import br.com.arantesrepresentacoes.pdv.entities.NcmAliquotaEstado;
import java.util.List;


@Repository
public interface NcmAliquotaEstadoRepository extends JpaRepository<NcmAliquotaEstado, UUID> {

    List<NcmAliquotaEstado> findByNcm(Ncm ncm);

}
