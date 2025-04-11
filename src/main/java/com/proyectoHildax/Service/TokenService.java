package com.proyectoHildax.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private static final SecureRandom random = new SecureRandom();
    private static final Map<String, Long> tokenExpirationTimes = new HashMap<>();
    private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(1); // 1 minuto de expiración

    // Método para generar el token
    public String generarToken() {
        int token = 10000 + random.nextInt(90000); // Genera un número de 5 dígitos
        String tokenStr = String.valueOf(token);
        // Guardar la hora de expiración del token
        tokenExpirationTimes.put(tokenStr, System.currentTimeMillis() + EXPIRATION_TIME);
        return tokenStr;
    }

    // Método para verificar si el token es válido
    public boolean verificarToken(String token) {
        Long expirationTime = tokenExpirationTimes.get(token);
        if (expirationTime == null) {
            return false; // El token no existe
        }

        if (System.currentTimeMillis() > expirationTime) {
            tokenExpirationTimes.remove(token); // Eliminar token expirado
            return false; // El token ha expirado
        }

        return true; // El token es válido
    }

    // Método opcional: Eliminar el token manualmente (si es necesario)
    public void eliminarToken(String token) {
        tokenExpirationTimes.remove(token); // Eliminar el token cuando ya no se necesite
    }
}
