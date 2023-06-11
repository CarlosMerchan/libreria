package com.poli.biblioteca.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "LIBROS")
public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID_LIBRO")
	private Integer idLibro;
	@JsonIgnoreProperties("libros")
	@ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name = "ID_AUTOR")
	private Autor autor;
	@Column(name = "NOMBRE")
	private String nombreLibro;
	@Column(name = "GENERO")
	private String genero;
	@Column(name = "ANO_PUBLICACION")
	private String anoPublicacion;
	@Column(name = "STOCK")
	private Integer stock;

	public Integer getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Integer idLibro) {
		this.idLibro = idLibro;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAnoPublicacion() {
		return anoPublicacion;
	}

	public void setAnoPublicacion(String anoPublicacion) {
		this.anoPublicacion = anoPublicacion;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Libro(String nombreLibro, String genero, String anoPublicacion, Integer stock) {
		super();		
		this.nombreLibro = nombreLibro;
		this.genero = genero;
		this.anoPublicacion = anoPublicacion;
		this.stock = stock;
	}

	public Libro() {
		super();
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", nombreLibro=" + nombreLibro + ", genero=" + genero + ", anoPublicacion="
				+ anoPublicacion + ", stock=" + stock + "]";
	}

	

}
