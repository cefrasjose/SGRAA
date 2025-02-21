package com.sgraa.controller;

import com.sgraa.model.Role;
import com.sgraa.model.Usuario;
import com.sgraa.repository.RoleRepository;
import com.sgraa.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> usuarioData) {
        String email = usuarioData.get("email");
        String senha = usuarioData.get("senha");
        String nome = usuarioData.get("nome");
        String roleName = usuarioData.get("role"); //ecebe o papel do usuário no corpo da requisição

        Optional<Usuario> usuarioExistente = usuarioService.buscarPorEmail(email);
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Erro: E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));

        //Definir papel conforme recebido na requisição
        Role role = roleRepository.findByNome(roleName.toUpperCase());
        if (role == null) {
            role = new Role();
            role.setNome(roleName.toUpperCase());
            roleRepository.save(role);
        }
        usuario.setRoles(Collections.singleton(role));

        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    //Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.get("email"),
                        credentials.get("senha")
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("Login realizado com sucesso!");
    }
}