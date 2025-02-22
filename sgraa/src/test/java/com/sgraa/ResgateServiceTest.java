package com.sgraa;

import com.sgraa.model.Animal;
import com.sgraa.model.Resgate;
import com.sgraa.service.ResgateService;
import com.sgraa.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResgateServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ResgateService resgateService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Registro de Resgate de Animal
    @Test
    @DisplayName("Registrar resgate com sucesso")
    void registrarResgateComSucesso() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setStatus("Resgatado");

        Resgate resgate = new Resgate();
        resgate.setAnimal(animal);
        resgate.setDescricao("Animal encontrado em situação de abandono.");

        when(resgateService.salvarResgate(any(Resgate.class))).thenReturn(resgate);

        Resgate novoResgate = resgateService.salvarResgate(resgate);

        assertNotNull(novoResgate);
        assertEquals("Resgatado", novoResgate.getAnimal().getStatus());
    }

    @Test
    @DisplayName("Tentativa de registrar resgate com dados incompletos")
    void registrarResgateComDadosIncompletos() {
        Resgate resgate = new Resgate();
        resgate.setDescricao(null);

        when(resgateService.salvarResgate(any(Resgate.class))).thenThrow(new IllegalArgumentException("Dados do resgate incompletos"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> resgateService.salvarResgate(resgate));
        assertEquals("Dados do resgate incompletos", exception.getMessage());
    }
}