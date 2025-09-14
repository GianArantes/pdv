package br.com.arantesrepresentacoes.pdv.dto;

import br.com.arantesrepresentacoes.pdv.entities.UserRole;
import br.com.arantesrepresentacoes.pdv.entities.UserStatus;

public record RegistroDTO(String nome, String email, String senha, UserRole role, UserStatus status) {

}
