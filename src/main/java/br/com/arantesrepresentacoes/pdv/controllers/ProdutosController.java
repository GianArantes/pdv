package br.com.arantesrepresentacoes.pdv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arantesrepresentacoes.pdv.dto.ProdutoDTO;
import br.com.arantesrepresentacoes.pdv.dto.ProdutoResponseDTO;
import br.com.arantesrepresentacoes.pdv.entities.Ncm;
import br.com.arantesrepresentacoes.pdv.entities.Produto;
import br.com.arantesrepresentacoes.pdv.repositories.NcmRepository;
import br.com.arantesrepresentacoes.pdv.repositories.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    ProdutosRepository produtosRepository;
    @Autowired
    NcmRepository ncmRepository;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Ncm ncm = ncmRepository.findNcmByCodigo(produtoDTO.ncm());
        if (ncm == null) {
            return ResponseEntity.badRequest().build();
        }
        Produto novoProduto = new Produto(produtoDTO.nome(), produtoDTO.precoBase(), produtoDTO.peso(), ncm);
        produtosRepository.save(novoProduto);
        return ResponseEntity.ok(novoProduto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> listaProdutos = this.produtosRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();

        return ResponseEntity.ok(listaProdutos);
    }

}
