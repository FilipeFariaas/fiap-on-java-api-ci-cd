package br.com.fiap.qualidade_agua.service;

import br.com.fiap.qualidade_agua.dto.QualidadeAguaCadastroDTO;
import br.com.fiap.qualidade_agua.dto.QualidadeAguaExibicaoDTO;
import br.com.fiap.qualidade_agua.exception.UsuarioNaoEncontradoException;
import br.com.fiap.qualidade_agua.model.QualidadeAgua;
import br.com.fiap.qualidade_agua.repository.QualidadeAguaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QualidadeAguaService {
    @Autowired
    QualidadeAguaRepository qualidadeAguaRepository;

    public QualidadeAguaExibicaoDTO gravar(QualidadeAguaCadastroDTO qualidadeAguaCadastroDTO) {
        QualidadeAgua qualidadeAgua = new QualidadeAgua();
        BeanUtils.copyProperties(qualidadeAguaCadastroDTO, qualidadeAgua);

        return new QualidadeAguaExibicaoDTO(qualidadeAguaRepository.save(qualidadeAgua));
    }

    public QualidadeAguaExibicaoDTO buscarPorId(Long id) {
        Optional<QualidadeAgua> qualidadeAguaOptional = qualidadeAguaRepository.findById(id);

        if(qualidadeAguaOptional.isPresent()) {
            return new QualidadeAguaExibicaoDTO(qualidadeAguaOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Registro não existente");
        }
    }

    public Page<QualidadeAguaExibicaoDTO> buscarTodosRegistros(Pageable paginacao) {
        return qualidadeAguaRepository.findAll(paginacao)
                .map(QualidadeAguaExibicaoDTO::new);
    }

    public void excluir(Long id) {
        Optional<QualidadeAgua> qualidadeAguaOptional = qualidadeAguaRepository.findById(id);

        if(qualidadeAguaOptional.isPresent()) {
            qualidadeAguaRepository.delete(qualidadeAguaOptional.get());
        } else {
            throw new RuntimeException("Registro não encontrado");
        }
    }

    public QualidadeAgua atualizar(QualidadeAgua qualidadeAgua) {
        Optional<QualidadeAgua> qualidadeAguaOptional = qualidadeAguaRepository.findById(qualidadeAgua.getId());

        if(qualidadeAguaOptional.isPresent()) {
            return qualidadeAguaRepository.save(qualidadeAgua);
        } else {
            throw new RuntimeException("Registro não encontrado");
        }
    }
}
