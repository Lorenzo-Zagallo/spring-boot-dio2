package com.learning.cursodio.auth.controllers;

import com.learning.cursodio.auth.services.UserService;
import com.learning.cursodio.config.jwt.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = userService.loadUserByUsername(username);
            String token = jwtTokenUtil.generateToken(String.valueOf(userDetails));

            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> userInfo) {
        try {
            userService.registerUser(
                    userInfo.get("username"),
                    userInfo.get("password"),
                    userInfo.get("rolename")
            );
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Erro: " + e.getMessage()); // Retorna a mensagem do erro
        }
    }


}
