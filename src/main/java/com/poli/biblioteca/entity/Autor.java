package com.poli.biblioteca.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;



@Entity
@Table(name="AUTORES")
public class Autor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="ID_AUTOR")	
	private Integer idAutor;
	
	@Column(name="NOMBRE_AUTOR")
	private String nombreAutor;
	
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	
	@Column(name="NACIONALIDAD")
	private String nacionalidad;
	@OneToMany(mappedBy = "autor",fetch = FetchType.LAZY )
	private List<Libro> libros;
	
	public Integer getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}
	public String getNombreAutor() {
		return nombreAutor;
	}
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public List<Libro> getLibros() {
		return libros;
	}
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	public Autor() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "Autor [idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", fechaNacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + "]";
	}
	
	
	public Autor(String nombreAutor, LocalDate fechaNacimiento, String nacionalidad) {
		super();
		this.nombreAutor = nombreAutor;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
	}
	public Autor(String nombreAutor, LocalDate fechaNacimiento, String nacionalidad, List<Libro> libros) {
		super();
		this.nombreAutor = nombreAutor;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.libros = libros;
	}
	public Autor(Integer idAutor) {
		super();
		this.idAutor = idAutor;
	}
	
	
	
	
	
}
