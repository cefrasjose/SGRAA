package com.sgraa;

import com.sgraa.model.Adocao;
import com.sgraa.model.Animal;
import com.sgraa.model.Pretendente;
import com.sgraa.service.AdocaoService;
import com.sgraa.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdocaoServiceTest {

    @Mock
    private AdocaoService adocaoService;

    @InjectMocks
    private UsuarioService usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Registro de Adoção de Animal
    @Test
    @DisplayName("Registrar adoção com sucesso")
    void registrarAdocaoComSucesso() {
        Pretendente pretendente = new Pretendente();
        pretendente.setId(1L);

        Animal animal = new Animal();
        animal.setId(1L);
        animal.setStatus("Disponível");

        Adocao adocao = new Adocao();
        adocao.setPretendente(pretendente);
        adocao.setAnimal(animal);
        adocao.setStatus("Em processo");

        when(AdocaoService.salvarAdocao(any(Adocao.class))).thenReturn(adocao);

        Adocao novaAdocao = adocaoService.salvarAdocao(adocao);

        assertNotNull(novaAdocao);
        assertEquals("Em processo", novaAdocao.getStatus());
    }

    @Test
    @DisplayName("Tentativa de adoção em animal já adotado")
    void tentarAdocaoAnimalAdotado() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setStatus("Adotado");

        Pretendente pretendente = new Pretendente();
        pretendente.setId(1L);

        Adocao adocao = new Adocao();
        adocao.setPretendente(pretendente);
        adocao.setAnimal(animal);
        adocao.setStatus("Em processo");

        when(AdocaoService.salvarAdocao(any(Adocao.class))).thenThrow(new IllegalStateException("Animal já adotado"));

        Exception exception = assertThrows(IllegalStateException.class, () -> AdocaoService.salvarAdocao(adocao));
        assertEquals("Animal já adotado", exception.getMessage());
    }
}