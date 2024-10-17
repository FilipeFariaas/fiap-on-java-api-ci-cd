package br.com.fiap.qualidade_agua.dto;

import br.com.fiap.qualidade_agua.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
    Long id,

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 10, message = "A senha deve conter enter 6 e 10 caracteres")
    String senha,

    @NotNull
    UsuarioRole role
) {
}
