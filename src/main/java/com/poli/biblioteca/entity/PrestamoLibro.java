package com.poli.biblioteca.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Table(name="PRESTAMOS")
public class PrestamoLibro  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRESTAMO")
	private Integer idPrestamo;	
	@JsonIgnoreProperties("prestamos")
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER )
	@JoinColumn(name="ID_CLIENTE")
	private Cliente idCliente;	
	@JsonIgnoreProperties("autor")
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER )
	@JoinColumn(name="ID_LIBRO")
	private Libro idLibro;
	@Column(name="FECHA_PRESTAMO")
	private LocalDate fechaPrestamo;
	@Column(name="FECHA_DEVOLUCION")
	private LocalDate fechaDevolucion;
	@Column(name="ESTADO")
	private String estado;
	
     
	
	 public Integer getIdPrestamo() {
		return idPrestamo;
	}



	public Cliente getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}



	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}



	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}



	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}



	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	


	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	


	public Libro getIdLibro() {
		return idLibro;
	}



	public void setIdLibro(Libro idLibro) {
		this.idLibro = idLibro;
	}



	public void setIdPrestamo(Integer idPrestamo) {
		this.idPrestamo = idPrestamo;
	}



	public PrestamoLibro() {
		 
	 }


	

	public PrestamoLibro(Libro idlibro,Cliente idCliente, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String estado) {
		super();
		this.idCliente = idCliente;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.estado = estado;
		this.idLibro = idlibro;
	}



	@Override
	public String toString() {
		return "PrestamoLibro [idLibro=" + idLibro + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion="
				+ fechaDevolucion + ", estado=" + estado + "]";
	}



	


	


	
	
	
}

