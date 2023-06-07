package com.poli.biblioteca.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Integer idCliente;
	@Column(name = "NOMBRES")
	private String nombres;
	@Column(name = "APELLIDOS")
	private String apellidos;
	@Column(name = "MAIL")
	private String mail;
	@Column(name = "TELEFONO")
	private Integer telefono;	
	@OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY  )
	private List<PrestamoLibro> prestamos;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public List<PrestamoLibro> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<PrestamoLibro> prestamos) {
		this.prestamos = prestamos;
	}

	public Cliente() {
		super();
	}

	

	public Cliente(String nombres, String apellidos, String mail, Integer telefono) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.mail = mail;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombres=" + nombres + ", apellidos=" + apellidos + ", mail="
				+ mail + ", telefono=" + telefono + "]";
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
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
	}
  
	

	

	

}
