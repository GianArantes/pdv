package br.com.arantesrepresentacoes.pdv.controllers;

import br.com.arantesrepresentacoes.pdv.dto.ProdutoResponseDTO;
import br.com.arantesrepresentacoes.pdv.entities.Produto;
import br.com.arantesrepresentacoes.pdv.repositories.NcmRepository;
import br.com.arantesrepresentacoes.pdv.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repository;

    @Autowired
    private NcmRepository ncmRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProdutoResponseDTO> getAll() {
        List<ProdutoResponseDTO> produtoList = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return produtoList;
    }

    @GetMapping("/listar")
    public String listar(Model model,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        List<Produto> produtos = repository.findAll();
        produtos.sort((a, b) -> a.getId().compareTo(b.getId()));
        model.addAttribute("produtos", produtos);
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "lista-produtos :: content";
        }
        return "lista-produtos";
    }

    @GetMapping("/novo")
    public String novo(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("ncms", ncmRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-produto :: content";
        }
        return "form-produto";
    }

    @PostMapping
    public String salvar(Produto produto) {
        repository.save(produto);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        repository.deleteById(id);
        return "redirect:/produtos/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {

        model.addAttribute("produto",
                repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado")));
        model.addAttribute("ncms", ncmRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-produto :: content";
        }
        return "form-produto";
    }

}
