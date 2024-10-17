package br.com.fiap.qualidade_agua.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record QualidadeAguaCadastroDTO(
    Long id,

    LocalDate atualizacao,

    @NotNull(message = "Campo obrigatório")
    Integer qualidade,

    @NotNull(message = "Campo obrigatório")
    @Size(min = 3, max = 5, message = "As opções são: BOM, RUIM ou ÓTIMO")
    String descricao
) {

}
