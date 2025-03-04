package com.proyectoHildax.Service;

import com.proyectoHildax.models.Producto;
import com.proyectoHildax.Repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> buscarPorEquipo(String equipo) {
        return productoRepository.buscarPorEquipo(equipo);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
}