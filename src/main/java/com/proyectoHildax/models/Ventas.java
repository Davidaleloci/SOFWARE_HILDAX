package com.proyectoHildax.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ventas {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta; // Esta propiedad debe mapear la columna 'id_venta'

    

    private BigDecimal total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;
    
    @ManyToOne
    @JoinColumn(name = "dni_usuario", referencedColumnName = "dni")
    private Usuario usuario; // Suponiendo que tienes una entidad Usuario

	public Ventas() {
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Optional<Usuario> usuario2) {
		this.usuario = usuario;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<DetalleVenta> getDetallesVenta() {
		return detallesVenta;
	}

	public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
		this.detallesVenta = detallesVenta;
	}

    // Getters y setters
}
