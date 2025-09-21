package br.com.arantesrepresentacoes.pdv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.arantesrepresentacoes.pdv.dto.UsuarioResponseDTO;
import br.com.arantesrepresentacoes.pdv.entities.Usuario;
import br.com.arantesrepresentacoes.pdv.repositories.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> listaUsuarios = this.usuariosRepository.findAll().stream().map(UsuarioResponseDTO::new)
                .toList();
        return ResponseEntity.ok(listaUsuarios);
    }

    @GetMapping("/listar")
    public String listar(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("usuarios", usuariosRepository.findAll());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "lista-usuarios :: content";
        }
        return "lista-usuarios";
    }

    @GetMapping("/novo")
    public String novo(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("usuario", new Usuario());
        if ("XMLHttpRequest".equals(requestedWith)) {
            return "form-usuario :: content";
        }
        return "form-usuario";
    }

    @PostMapping
    public String salvar(Usuario usuario) {
        usuariosRepository.save(usuario);
        return "redirect:/usuarios/listar";
    }
}
