package br.com.arantesrepresentacoes.pdv.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private LocalDate dataVenda;
    private BigDecimal descontoGeral;
    private Double pesoTotal;
    private BigDecimal valorTotalLiquido;
    private BigDecimal valorSt;
    private BigDecimal valorTotal;
    private Cliente cliente;
    private Usuario representante;


}
