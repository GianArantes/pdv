package br.com.arantesrepresentacoes.pdv.entities;

import java.sql.Blob;
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
@Table(name = "venda_anexos")
public class VendaAnexo {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nomeArquivo;
    private String tipoArquivo;
    private Blob dadosArquivo;
}
