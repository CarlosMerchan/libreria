package com.poli.biblioteca.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poli.biblioteca.dao.IAutoresDao;
import com.poli.biblioteca.entity.Autor;
import com.poli.biblioteca.entity.Cliente;
import com.poli.biblioteca.entity.Libro;
import com.poli.biblioteca.entity.PrestamoLibro;
import com.poli.biblioteca.service.BibliotecaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {
	
	@Autowired
	private BibliotecaService bibliotecaService;
	
	@GetMapping("/listaLibros")
	public ResponseEntity<List<Libro>> listaLibros(){			
		return bibliotecaService.getListaLibros();
	}
	
	@GetMapping("/listarAutores")
	public ResponseEntity<?> listarAutores(){
		return bibliotecaService.listarAutores();
	}
	
	@PostMapping("/registrarAutor")
	public ResponseEntity<String> insertarAutor(@RequestBody Autor autor){
		return bibliotecaService.registrarAutor(autor);
	}
	
	@DeleteMapping("/eliminarAutor/{id}")
	public ResponseEntity<String> insertarAutor(@PathVariable int id){
		return bibliotecaService.eliminarAutor(id);
	}
	
	@PostMapping("/registrarCliente")
	public ResponseEntity<String> insertarCliente(@RequestBody Cliente cliente){
		return bibliotecaService.registarCliente(cliente);
	}
	
	@PostMapping("/registrarLibro")
	public ResponseEntity<String> insertarLibro(@RequestBody Libro libro){
		return bibliotecaService.registarLibro(libro);
	}
	
	@GetMapping("/librosxAutor/{idAutor}")
	public ResponseEntity<List<Libro>> obtenerLibrosxAutor(@PathVariable int idAutor){
		return bibliotecaService.obtenerLibros(idAutor);
		
	}	
	
	@GetMapping("/obtenerLibro/{idLibro}")
	public ResponseEntity<Libro> obtenerLibro(@PathVariable int idLibro){
		return bibliotecaService.getLibro(idLibro);
				
	}
	@PostMapping("/solicitarPrestamo/{idLibro}/{documento}")
	public ResponseEntity<String> solicitarPrestamo(@PathVariable int idLibro,@PathVariable("documento") Long documento){
		return bibliotecaService.solicitarPrestamo(idLibro,documento);
	}
	
	@GetMapping("/listarPrestamos")
	public ResponseEntity<List<PrestamoLibro>> listarPrestamos(){
		return bibliotecaService.listarPrestamos();
	}
	
	@DeleteMapping("/eliminarPrestamo/{idPrestamo}/{idLibro}")
	public ResponseEntity<String> eliminarPrestamo(@PathVariable int idPrestamo,@PathVariable int idLibro){
		return bibliotecaService.eliminarPrestamo(idPrestamo,idLibro);
	}
	
	@GetMapping("/listarPrestamoCliente/{idCliente}")
	public ResponseEntity<List<PrestamoLibro>> prestamosCliente(@PathVariable Long idCliente){
		return bibliotecaService.prestamosCliente(idCliente);
	}
	
	@GetMapping("listarClientes")
	public ResponseEntity<?> listarClientes(){
		return bibliotecaService.listarClientes();
	}
	
	@PutMapping("actualizarEstado/{estado}/{idPrestamo}/{idLibro}")
	public ResponseEntity<?>  actualizarEstado(@PathVariable int estado,@PathVariable int idPrestamos,@PathVariable int idLibro){
		return bibliotecaService.actualizarEstado(estado,idPrestamos,idLibro);
	}


}
