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
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY )
	@JoinColumn(name="ID_CLIENTE")
	private Cliente idCliente;
	@Column(name="FECHA_PRESTAMO")
	private LocalDate fechaPrestamo;
	@Column(name="FECHA_DEVOLUCION")
	private LocalDate fechaDevolucion;
	
     
	
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



	public PrestamoLibro() {
		 
	 }


	

	public PrestamoLibro(Cliente idCliente, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
		super();
		this.idCliente = idCliente;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}



	@Override
	public String toString() {
		return "PrestamoLibro [idPrestamo=" + idPrestamo + ", idCliente=" + idCliente + ", fechaPrestamo="
				+ fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoLibro other = (PrestamoLibro) obj;
		return Objects.equals(idCliente, other.idCliente);
	}



	


	
	
	
}

