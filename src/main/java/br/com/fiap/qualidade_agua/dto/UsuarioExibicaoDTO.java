package br.com.fiap.qualidade_agua.dto;

import br.com.fiap.qualidade_agua.model.Usuario;
import br.com.fiap.qualidade_agua.model.UsuarioRole;


public record UsuarioExibicaoDTO(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
