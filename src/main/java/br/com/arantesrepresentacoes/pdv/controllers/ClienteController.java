package br.com.arantesrepresentacoes.pdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arantesrepresentacoes.pdv.entities.Cliente;
import br.com.arantesrepresentacoes.pdv.repositories.ClienteRepository;
import br.com.arantesrepresentacoes.pdv.repositories.UsuarioRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String listar(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("clientes", clienteRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "lista-clientes :: content";
        }
        return "lista-clientes";
    }

    @GetMapping("/novo")
    public String novo(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("representantes", usuarioRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-cliente :: content";
        }
        return "form-cliente";
    }

    @PostMapping("/salvar")
    public String salvar(Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable UUID id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente inválido:" + id));
        model.addAttribute("cliente", cliente);
        model.addAttribute("representantes", usuarioRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-cliente :: content";
        }
        return "form-cliente";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable UUID id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes/listar";
    }
}
