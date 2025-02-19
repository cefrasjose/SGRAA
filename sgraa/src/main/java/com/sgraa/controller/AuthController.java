package com.sgraa.controller;

import com.sgraa.model.Role;
import com.sgraa.model.Usuario;
import com.sgraa.repository.RoleRepository;
import com.sgraa.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Endpoint para registrar um novo usuário
    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        // Verifica se o e-mail já está cadastrado
        if (usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Define o papel padrão como "VOLUNTARIO" se não for especificado
        Role role = roleRepository.findByNome("VOLUNTARIO");
        if (role == null) {
            role = new Role();
            role.setNome("VOLUNTARIO");
            roleRepository.save(role);
        }
        usuario.setRoles(Collections.singleton(role));

        // Salva o usuário no banco
        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    //Endpoint para verificar se o usuário está autenticado (sessão ativa)
    @GetMapping("/me")
    public ResponseEntity<Usuario> getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }
}
