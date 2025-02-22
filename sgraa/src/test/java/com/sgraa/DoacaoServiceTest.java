package com.sgraa;

import com.sgraa.model.Doacao;
import com.sgraa.service.DoacaoService;
import com.sgraa.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoacaoServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private DoacaoService doacaoService;

    @InjectMocks
    private UsuarioService usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Registro de Doação de Recursos
    @Test
    @DisplayName("Registrar doação válida")
    void registrarDoacaoValida() {
        Doacao doacao = new Doacao();
        doacao.setDescricao("Ração");
        doacao.setQuantidade(10);

        when(doacaoService.salvarDoacao(any(Doacao.class))).thenReturn(doacao);

        Doacao novaDoacao = doacaoService.salvarDoacao(doacao);

        assertNotNull(novaDoacao);
        assertEquals("Ração", novaDoacao.getDescricao());
        assertEquals(10, novaDoacao.getQuantidade());
    }

    @Test
    @DisplayName("Tentativa de registrar doação com valor negativo")
    void registrarDoacaoComValorNegativo() {
        Doacao doacao = new Doacao();
        doacao.setDescricao("Ração");
        doacao.setQuantidade(-5);

        when(doacaoService.salvarDoacao(any(Doacao.class))).thenThrow(new IllegalArgumentException("Quantidade inválida"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> doacaoService.salvarDoacao(doacao));
        assertEquals("Quantidade inválida", exception.getMessage());
    }
}