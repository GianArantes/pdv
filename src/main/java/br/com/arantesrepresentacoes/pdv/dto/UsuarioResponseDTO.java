package br.com.arantesrepresentacoes.pdv.dto;

import br.com.arantesrepresentacoes.pdv.entities.Usuario;

public record UsuarioResponseDTO(String id, String nome, String email, String role, String status) {

    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId().toString(), usuario.getNome().toString(), usuario.getEmail().toString(), usuario.getRole().toString(), usuario.getStatus().toString());
    }

}
