package com.poli.biblioteca.Utils;

import java.util.Optional;

import com.poli.biblioteca.entity.Cliente;
import com.poli.biblioteca.entity.Libro;

public class Datos {

	public static Optional<Libro> crearLibro1 (){
		return Optional.of(new Libro("El retrato de dorian gray", "Gotico", "1890", 2));
	};
	
	public static Optional<Libro> crearLibro2(){
		return Optional.of(new Libro("El diablo en la botella", "Ficcion", "1891", 0));
	};
	
	public static Optional<Libro> crearLibro3(){
		return Optional.of(new Libro("Masacre en las bananeras", "Narrativa", "1890", 5));
	};
	
	
	public static Optional<Cliente> crearCliente1(){
		return Optional.of(new Cliente(11111L,"Carlos Alberto", "Merchan Munoz", "prueba1@gmail.com", 21111));
	};
	
	public static Optional<Cliente> crearCliente2(){
		return Optional.of(new Cliente(22222L,"Camilo Arturo", "Lopez", "prueba2@gmail.com", 21111));
	};
}
