package br.com.fiap.qualidade_agua.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_QUALIDADE_AGUA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QualidadeAgua {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "T_QUALIDADE_AGUA_SEQ"
    )
    @SequenceGenerator(
            name = "T_QUALIDADE_AGUA_SEQ",
            sequenceName = "T_QUALIDADE_AGUA_SEQ",
            allocationSize = 1
    )
    private Long id;
    private LocalDate atualizacao;
    private Integer qualidade;
    private String descricao;
}
