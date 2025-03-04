package com.proyectoHildax.Controller;



import com.proyectoHildax.models.Producto;
import com.proyectoHildax.Service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/buscar/{equipo}")
    public List<Producto> buscarPorEquipo(@PathVariable String equipo) {
        return productoService.buscarPorEquipo(equipo);
    }

    @GetMapping("/todos")
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }
}