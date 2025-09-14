package br.com.arantesrepresentacoes.pdv.dto;

import br.com.arantesrepresentacoes.pdv.entities.Produto;

public record ProdutoResponseDTO(String id, String nome, Double preco, Double peso, String ncm) {
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId().toString(), produto.getNome(), produto.getPrecoBase(), produto.getPeso(),
                produto.getNcm().getCodigo());
    }
}