package br.com.arantesrepresentacoes.pdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.arantesrepresentacoes.infra.security.TokenService;
import br.com.arantesrepresentacoes.pdv.dto.AuthenticationDTO;
import br.com.arantesrepresentacoes.pdv.dto.RegistroDTO;
import br.com.arantesrepresentacoes.pdv.entities.Usuario;
import br.com.arantesrepresentacoes.pdv.repositories.UsuarioRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse response) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
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

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
