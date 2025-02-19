package com.sgraa.controller;

import com.sgraa.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        if ("admin".equals(loginDTO.getUsername()) && "admin".equals(loginDTO.getPassword())) {
            return ResponseEntity.ok("Token Simulado: 123456");
        }
        return ResponseEntity.status(401).body("Usuário ou senha inválidos");
    }
}
