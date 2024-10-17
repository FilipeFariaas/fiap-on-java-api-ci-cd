package br.com.fiap.qualidade_agua.config.security;

import br.com.fiap.qualidade_agua.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${minha.chave.secreta}")
    private String palavraSecreta;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            String token = JWT
                    .create()
                    .withIssuer("contatos")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);

            System.out.println("gerarToken - " + token);
            return token;
        } catch (Exception exception) {
            System.out.println("Falaha ao gerar Token JWT");
            throw new RuntimeException("Falaha ao gerar Token JWT");
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);
            String jwt = JWT.require(algorithm)
                    .withIssuer("contatos")
                    .build()
                    .verify(token)
                    .getSubject();
            System.out.println(jwt);

            return jwt;
        } catch (JWTVerificationException exception) {
//            throw new RuntimeException("Falha ao verificar Token JWT");
            return "Falha ao verificar Token JWT: " + exception.getMessage();
        }
    }

    public Instant gerarDataDeExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
