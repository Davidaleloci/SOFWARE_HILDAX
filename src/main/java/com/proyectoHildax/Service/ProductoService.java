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
    

    // Obtener todos los productos activos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Agregar un nuevo producto
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Editar un producto existente
    public Producto actualizarProducto(Long id, Producto nuevoProducto) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(nuevoProducto.getNombre());
                    producto.setCategoria(nuevoProducto.getCategoria());
                    producto.setCantidad(nuevoProducto.getCantidad());
                    producto.setPrecio(nuevoProducto.getPrecio());
                    producto.setTalla(nuevoProducto.getTalla());
                    producto.setEstado(nuevoProducto.getEstado());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Eliminación lógica (cambia estado a 1)
    public Producto eliminarProducto(Long id) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setEstado(false); // Marcar como eliminado
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Restaurar un producto eliminado (cambia estado a 0)
    public Producto restaurarProducto(Long id) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setEstado(true); // Restaurar el producto
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Eliminación real del producto en la base de datos
    public void borrarProductoDefinitivamente(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
    public boolean existeProducto(String nombre, String categoria, String talla) {
        return productoRepository.existsByNombreAndCategoriaAndTalla(nombre, categoria, talla);
    }


}
