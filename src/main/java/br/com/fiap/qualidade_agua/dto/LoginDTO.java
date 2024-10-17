package br.com.fiap.qualidade_agua.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "Senha é obrigatória")
//    @Size(min = 6, max = 10, message = "A senha deve conter enter 6 e 10 caracteres")
    String senha
) {
}