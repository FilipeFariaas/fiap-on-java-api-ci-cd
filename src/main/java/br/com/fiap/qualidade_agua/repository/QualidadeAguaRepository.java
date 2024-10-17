package br.com.fiap.qualidade_agua.repository;

import br.com.fiap.qualidade_agua.model.QualidadeAgua;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualidadeAguaRepository extends JpaRepository<QualidadeAgua, Long> {
}
