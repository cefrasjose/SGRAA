package com.sgraa;

import com.sgraa.model.Usuario;
import com.sgraa.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Cadastro de Usuário
    @Test
    @DisplayName("Cadastrar novo voluntário com sucesso")
    void cadastrarNovoVoluntarioComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos Silva");
        usuario.setEmail("carlos@teste.com");
        usuario.setSenha("123456");

        when(passwordEncoder.encode(usuario.getSenha())).thenReturn("encryptedPassword");
        when(usuarioService.salvarUsuario(any(Usuario.class))).thenReturn(usuario);

        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);

        assertNotNull(novoUsuario);
        assertEquals("Carlos Silva", novoUsuario.getNome());
        assertEquals("carlos@teste.com", novoUsuario.getEmail());
    }

    @Test
    @DisplayName("Cadastro de usuário com e-mail duplicado")
    void cadastroComEmailDuplicado() {
        String email = "carlos@teste.com";
        when(usuarioService.buscarPorEmail(email)).thenReturn(Optional.of(new Usuario()));

        Optional<Usuario> resultado = usuarioService.buscarPorEmail(email);

        assertTrue(resultado.isPresent(), "O e-mail já deve estar cadastrado.");
    }

    //Login de Usuário
    @Test
    @DisplayName("Login com credenciais válidas")
    void loginComCredenciaisValidas() {
        String email = "carlos@teste.com";
        String senha = "123456";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));

        when(usuarioService.buscarPorEmail(email)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches(senha, usuario.getSenha())).thenReturn(true);

        Optional<Usuario> resultado = usuarioService.buscarPorEmail(email);

        assertTrue(resultado.isPresent(), "Usuário deve ser autenticado com sucesso.");
    }

    @Test
    @DisplayName("Login com senha incorreta")
    void loginComSenhaIncorreta() {
        String email = "carlos@teste.com";
        String senha = "senhaErrada";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode("123456"));

        when(usuarioService.buscarPorEmail(email)).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches(senha, usuario.getSenha())).thenReturn(false);

        Optional<Usuario> resultado = usuarioService.buscarPorEmail(email);

        assertTrue(resultado.isPresent(), "Usuário encontrado, mas senha incorreta.");
        assertFalse(passwordEncoder.matches(senha, usuario.getSenha()), "Senha deve ser inválida.");
    }
}