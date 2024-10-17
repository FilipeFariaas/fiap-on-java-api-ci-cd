package br.com.fiap.qualidade_agua.controller;

import br.com.fiap.qualidade_agua.config.security.TokenService;
import br.com.fiap.qualidade_agua.dto.LoginDTO;
import br.com.fiap.qualidade_agua.dto.TokenDTO;
import br.com.fiap.qualidade_agua.dto.UsuarioCadastroDTO;
import br.com.fiap.qualidade_agua.dto.UsuarioExibicaoDTO;
import br.com.fiap.qualidade_agua.model.Usuario;
import br.com.fiap.qualidade_agua.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.senha()
                );
        System.out.println(usernamePassword);

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        System.out.println("auth: " + auth);
        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        System.out.println("login controller - " + token);

//        return ResponseEntity.ok(new TokenDTO(token));
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        UsuarioExibicaoDTO usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDTO);
        return usuarioSalvo;
    }
}
