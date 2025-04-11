package com.proyectoHildax.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoHildax.models.Ventas;

@Repository
public interface VentaRepository extends JpaRepository<Ventas, Long> {
}