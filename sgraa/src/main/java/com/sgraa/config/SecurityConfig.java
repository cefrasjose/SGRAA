package com.sgraa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Permitir login e registro sem autenticação
                        .requestMatchers("/api/animais/**").hasRole("ADMIN") // Somente ADMIN pode acessar
                        .requestMatchers("/api/voluntarios/**").hasAnyRole("ADMIN", "VOLUNTARIO") // ADMIN e VOLUNTARIO podem acessar
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/api/auth/login") // Login via formulário
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .permitAll()
                );

        return http.build();
    }
}
