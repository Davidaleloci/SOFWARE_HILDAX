package com.proyectoHildax.Controller;

import com.proyectoHildax.models.LoginRequest;
import com.proyectoHildax.models.Usuario;
import com.proyectoHildax.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (no recomendado para producción)

public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("🔹 Intentando login con DNI: " + request.getDni());
        System.out.println("🔹 Contraseña recibida: " + request.getContrasena());

        Optional<Usuario> usuarioOpt = usuarioRepository.findByDni(request.getDni());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            System.out.println("✅ Usuario encontrado en la BD: " + usuario.getDni());

            // Comparación de contraseña
            if (usuario.getContrasena().equals(request.getContrasena())) {
                System.out.println("✅ Contraseña correcta");
                return ResponseEntity.ok(Map.of(
                    "token", "fake-jwt-token",
                    "usuario", Map.of(
                        "dni", usuario.getDni(),
                        "nombre", usuario.getNombre(),
                        "apellido", usuario.getApellido(),
                        "rol", usuario.getRol()
                    )
                ));
            } else {
                System.out.println("❌ Contraseña incorrecta");
            }
        } else {
            System.out.println("❌ Usuario no encontrado en la BD");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }

}
