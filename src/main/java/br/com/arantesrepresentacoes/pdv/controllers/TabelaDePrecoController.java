package br.com.arantesrepresentacoes.pdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arantesrepresentacoes.pdv.repositories.ProdutosRepository;
import br.com.arantesrepresentacoes.pdv.repositories.TabelaDePrecoRepository;

@Controller
@RequestMapping("/tabela_de_preco")
public class TabelaDePrecoController {

@Autowired
private TabelaDePrecoRepository repository;

@Autowired 
private ProdutosRepository produtosRepository;




}
