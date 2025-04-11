package com.proyectoHildax.Controller;

import com.proyectoHildax.Repository.ProductoRepository;
import com.proyectoHildax.Service.ProductoService;
import com.proyectoHildax.models.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository; // Agregar el repositorio

    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos activos
    @GetMapping("/todos")
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    // Agregar un nuevo producto
    @PostMapping("/agregar")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.agregarProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Editar un producto existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminación lógica (cambia estado a 1)
    @PutMapping("/eliminar/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {
        Producto productoEliminado = productoService.eliminarProducto(id);
        return ResponseEntity.ok(productoEliminado);
    }

    // Restaurar un producto eliminado (cambia estado a 0)
    @PutMapping("/restaurar/{id}")
    public ResponseEntity<Producto> restaurarProducto(@PathVariable Long id) {
        Producto productoRestaurado = productoService.restaurarProducto(id);
        return ResponseEntity.ok(productoRestaurado);
    }

    // Eliminación definitiva del producto en la base de datos
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id) {
        productoService.borrarProductoDefinitivamente(id);
        return ResponseEntity.noContent().build();
    }
    // Verificar si un producto ya existe por nombre
 // Verificar si un producto con el mismo nombre, categoría y talla ya existe
    @GetMapping("/existe")
    public ResponseEntity<Boolean> verificarExistencia(
        @RequestParam String nombre,
        @RequestParam String categoria,
        @RequestParam String talla) {
        
        boolean existe = productoService.existeProducto(nombre, categoria, talla);
        return ResponseEntity.ok(existe);
    }

}
