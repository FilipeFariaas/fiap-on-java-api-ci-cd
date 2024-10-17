package br.com.fiap.qualidade_agua.controller;

import br.com.fiap.qualidade_agua.dto.QualidadeAguaCadastroDTO;
import br.com.fiap.qualidade_agua.dto.QualidadeAguaExibicaoDTO;
import br.com.fiap.qualidade_agua.model.QualidadeAgua;
import br.com.fiap.qualidade_agua.service.QualidadeAguaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QualidadeAguaController {
    @Autowired
    private QualidadeAguaService qualidadeAguaService;

    @PostMapping("/qualidade_agua")
    @ResponseStatus(HttpStatus.CREATED)
    public QualidadeAguaExibicaoDTO gravar(@RequestBody @Valid QualidadeAguaCadastroDTO qualidadeAguaCadastroDTO) {
        return qualidadeAguaService.gravar(qualidadeAguaCadastroDTO);
    }

    @GetMapping("/qualidade_agua")
    @ResponseStatus(HttpStatus.OK)
    public Page<QualidadeAguaExibicaoDTO> listarTodosRegistros(Pageable paginacao) {
        return qualidadeAguaService.buscarTodosRegistros(paginacao);
    }

    @GetMapping("/qualidade_agua/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QualidadeAguaExibicaoDTO buscarPorId(@PathVariable Long id) {
        return qualidadeAguaService.buscarPorId(id);
    }

    @DeleteMapping("/qualidade_agua/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        qualidadeAguaService.excluir(id);
    }

    @PutMapping("/qualidade_agua")
    @ResponseStatus(HttpStatus.OK)
    public QualidadeAgua atualizar(@RequestBody QualidadeAgua qualidadeAgua) {
        return qualidadeAguaService.atualizar(qualidadeAgua);
    }

}
