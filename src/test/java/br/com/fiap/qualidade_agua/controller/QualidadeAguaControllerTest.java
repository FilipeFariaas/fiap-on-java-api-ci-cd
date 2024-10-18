package br.com.fiap.qualidade_agua.controller;

import br.com.fiap.qualidade_agua.controller.QualidadeAguaController;
import br.com.fiap.qualidade_agua.dto.QualidadeAguaExibicaoDTO;
import br.com.fiap.qualidade_agua.service.QualidadeAguaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QualidadeAguaController.class)
class QualidadeAguaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QualidadeAguaService qualidadeAguaService;

    @MockBean
    QualidadeAguaController qualidadeAguaController = new QualidadeAguaController();

    @Test
    void deveChamarServicoListarTodosRegistros() {
        // Given
        when(qualidadeAguaService.buscarTodosRegistros(any())).thenReturn(Page.empty());

        // When
        qualidadeAguaController.listarTodosRegistros(Pageable.unpaged());

        // Then
        verify(qualidadeAguaService).buscarTodosRegistros(any());
    }
}