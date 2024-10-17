package br.com.fiap.qualidade_agua.dto;

import br.com.fiap.qualidade_agua.model.QualidadeAgua;

import java.time.LocalDate;

public record QualidadeAguaExibicaoDTO(
    Long id,
    LocalDate atualizacao,
    Integer qualidade,
    String descricao
) {
    public QualidadeAguaExibicaoDTO(QualidadeAgua qualidadeAgua) {
        this(
                qualidadeAgua.getId(),
                qualidadeAgua.getAtualizacao(),
                qualidadeAgua.getQualidade(),
                qualidadeAgua.getDescricao()
        );
    }
}
