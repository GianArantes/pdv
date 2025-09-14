package br.com.arantesrepresentacoes.pdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arantesrepresentacoes.infra.security.TokenService;
import br.com.arantesrepresentacoes.pdv.dto.AuthenticationDTO;
import br.com.arantesrepresentacoes.pdv.dto.LoginResponseDTO;
import br.com.arantesrepresentacoes.pdv.dto.RegistroDTO;
import br.com.arantesrepresentacoes.pdv.entities.Usuario;
import br.com.arantesrepresentacoes.pdv.repositories.UsuarioRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO authDTO) {
        var loginSenha = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.senha());
        var auth = authenticationManager.authenticate(loginSenha);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody @Valid RegistroDTO registroDTO) {
        if (this.usuarioRepository.findByEmail(registroDTO.email()) != null)
            return ResponseEntity.badRequest().build();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(registroDTO.senha());

        Usuario novoUsuario = new Usuario(
                registroDTO.nome(),
                registroDTO.email(),
                senhaCriptografada,
                registroDTO.role(),
                registroDTO.status());
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

}
