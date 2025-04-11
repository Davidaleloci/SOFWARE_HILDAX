package com.proyectoHildax.Service;

import com.proyectoHildax.Repository.UsuarioRepository;
import com.proyectoHildax.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorDni(String dni) {
        return usuarioRepository.findById(dni);
    }

    public void eliminarUsuario(String dni) {
        usuarioRepository.deleteById(dni);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
