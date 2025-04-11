package com.proyectoHildax.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoHildax.Service.TokenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
public class AdminController {

    private final TokenService tokenService;

    public AdminController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/generar-token")
    public String generarToken() {
        return tokenService.generarToken();
    }

    @GetMapping("/verificar-token")
    public boolean verificarToken(@RequestParam String token) {
        return tokenService.verificarToken(token);
    }
}
