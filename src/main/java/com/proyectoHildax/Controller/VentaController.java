package com.proyectoHildax.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoHildax.Service.VentaService;
import com.proyectoHildax.models.Ventas;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:4200") // Permitir acceso solo desde localhost:4200

public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping("/registrar")
    public ResponseEntity<Ventas> registrarVenta(Ventas venta, String dniUsuario) {
        try {
            // Llamar al servicio con los parámetros correctos
            Ventas ventaRegistrada = ventaService.registrarVenta(venta, dniUsuario);
            return new ResponseEntity<>(ventaRegistrada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
