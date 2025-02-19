package com.sgraa.controller;

import com.sgraa.model.Role;
import com.sgraa.model.Usuario;
import com.sgraa.repository.RoleRepository;
import com.sgraa.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

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

    //Endpoint de Registro
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.buscarPorEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Email já cadastrado.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        //Define papel padrão como VOLUNTARIO se não for informado
        Role role = roleRepository.findByNome("VOLUNTARIO");
        if (role == null) {
            role = new Role();
            role.setNome("VOLUNTARIO");
            roleRepository.save(role);
        }
        usuario.setRoles(Collections.singleton(role));

        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }
}