package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody UserDetails userDetails) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword())
        );

        UserDetails authenticatedUser = userDetailsService.loadUserByUsername(userDetails.getUsername());
        // Implementar la lógica de generación de token según tus necesidades
        String token = generateToken(authenticatedUser);

        // Devolver el token o cualquier otra respuesta necesaria
        return ResponseEntity.ok(token);
    }

    private String generateToken(UserDetails userDetails) {
        // Implementar la lógica de generación de token según tus necesidades
        // Puedes usar JwtTokenProvider u otra clase de utilidad
        // En este ejemplo, simplemente devolvemos un String fijo
        return "yourGeneratedToken";
    }
}
