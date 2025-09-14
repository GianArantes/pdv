package br.com.arantesrepresentacoes.pdv.entities;

import java.math.BigDecimal;
import java.util.UUID;
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
@Table(name = "venda_itens")
public class VendaItem {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;
    private BigDecimal precoUnitario;
    private BigDecimal desconto;
    private BigDecimal precoTotal;
    private Double pesoTotal;
    private BigDecimal valorSt;


}
