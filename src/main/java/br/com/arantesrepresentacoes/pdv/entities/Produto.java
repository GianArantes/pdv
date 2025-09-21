package br.com.arantesrepresentacoes.pdv.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double peso;
    @ManyToOne
    @JoinColumn(name = "ncm_id")
    private Ncm ncm;

    public Produto(String nome, Double peso, Ncm ncm) {
        this.nome = nome;
        this.peso = peso;
        this.ncm = ncm;
    }
    


}
