package com.proyectoHildax.Repository;

import com.proyectoHildax.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByDni(String dni);
    List<Usuario> findByEstadoTrue();

    

}
