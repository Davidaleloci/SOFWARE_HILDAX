package com.proyectoHildax.models;

public class LoginRequest {
    private String dni;
    private String contrasena;

    // Constructor vacío (necesario para deserialización)
    public LoginRequest() {
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
