package br.com.arantesrepresentacoes.pdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.arantesrepresentacoes.pdv.repositories.UsuarioRepository;

@Service

public class AutorizaService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByEmail(username);
    }

}
