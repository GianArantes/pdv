package br.com.arantesrepresentacoes.pdv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.arantesrepresentacoes.pdv.dto.UsuarioResponseDTO;
import br.com.arantesrepresentacoes.pdv.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> listaUsuarios = this.usuariosRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
        return ResponseEntity.ok(listaUsuarios);
    }

}
