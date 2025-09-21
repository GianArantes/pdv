package br.com.arantesrepresentacoes.pdv.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "NcmAliquotaEstado")
public class NcmAliquotaEstado {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(length = 2)
    private String estado;
    private BigDecimal aliquota;

    @ManyToOne
    @JoinColumn(name = "ncm_id")
    private Ncm ncm;

}
