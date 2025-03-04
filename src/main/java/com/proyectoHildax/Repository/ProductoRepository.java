package com.proyectoHildax.Repository;

import com.proyectoHildax.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    @Query("SELECT p FROM Producto p WHERE LOWER(p.equipo) = LOWER(:equipo)")
    List<Producto> buscarPorEquipo(@Param("equipo") String equipo);
}
