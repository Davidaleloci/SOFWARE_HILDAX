package com.proyectoHildax.Service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoHildax.Repository.DetalleVentaRepository;
import com.proyectoHildax.Repository.ProductoRepository;
import com.proyectoHildax.Repository.UsuarioRepository;
import com.proyectoHildax.Repository.VentaRepository;
import com.proyectoHildax.models.DetalleVenta;
import com.proyectoHildax.models.Producto;
import com.proyectoHildax.models.Usuario;
import com.proyectoHildax.models.Ventas;

import jakarta.transaction.Transactional;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Ventas registrarVenta(Ventas venta, String dniUsuario) {
        BigDecimal totalVenta = BigDecimal.ZERO;

        // Verificar que la venta tenga detalles
        if (venta.getDetallesVenta() == null || venta.getDetallesVenta().isEmpty()) {
            throw new RuntimeException("La venta debe tener detalles");
        }

        // Obtener el usuario por el dni
        Optional<Usuario> usuario = usuarioRepository.findByDni(dniUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Asignar el usuario a la venta
        venta.setUsuario(usuario);

        for (DetalleVenta detalle : venta.getDetallesVenta()) {
            Producto producto = obtenerProductoPorId(detalle.getProducto().getIdProducto());

            // Verificar si hay stock suficiente
            if (producto.getCantidad() >= detalle.getCantidad()) {
                // Actualizar stock y calcular subtotal
                actualizarStock(producto, detalle.getCantidad());
                BigDecimal subtotal = calcularSubtotal(producto, detalle.getCantidad());
                detalle.setSubtotal(subtotal);
                totalVenta = totalVenta.add(subtotal);
            } else {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }

        // Establecer el total de la venta
        venta.setTotal(totalVenta);

        // Guardar la venta y los detalles
        return ventaRepository.save(venta);
    }
    private Producto obtenerProductoPorId(Long idProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	private void actualizarStock(Producto producto, int cantidadVendida) {
        producto.setCantidad(producto.getCantidad() - cantidadVendida);
        productoRepository.save(producto);
    }
    private BigDecimal calcularSubtotal(Producto producto, int cantidad) {
        BigDecimal precioProducto = BigDecimal.valueOf(producto.getPrecio());
        return precioProducto.multiply(BigDecimal.valueOf(cantidad));
    }

}
