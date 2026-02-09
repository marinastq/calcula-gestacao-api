package br.com.marinas.calculo_gestacao;

import br.com.marinas.calculo_gestacao.model.Classificacao;
import br.com.marinas.calculo_gestacao.model.Gestacao;
import br.com.marinas.calculo_gestacao.service.GestacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import br.com.marinas.calculo_gestacao.controller.GestacaoController;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import java.time.LocalDate;

@WebMvcTest(GestacaoController.class)
public class GestacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GestacaoService gestacaoService;

    @Test
    @DisplayName("deve gravar a gestacao com sucesso")
    void deveGravarGestacaoComSucesso() throws Exception {
        Gestacao gestacao = buildMockGestacao();

        when(gestacaoService.criarGestacao(any(Gestacao.class))).thenReturn(gestacao);

        mockMvc.perform(
                post("/v1/gestacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Fulana",
                                  "dataDUM": "2024-12-28",
                                  "classificacao": "GESTACAO_NORMAL"
                                }
                                """)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Fulana"));
    }


    private Gestacao buildMockGestacao() {
        return new Gestacao(
                "Fulana",
                LocalDate.parse("2024-12-28"),
                Classificacao.GESTACAO_NORMAL
        );
    }
}
