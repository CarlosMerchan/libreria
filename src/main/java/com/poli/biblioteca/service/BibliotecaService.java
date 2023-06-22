package com.poli.biblioteca.service;

import java.security.InvalidAlgorithmParameterException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.catalina.connector.Response;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.xml.sax.ErrorHandler;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.poli.biblioteca.dao.IAutoresDao;
import com.poli.biblioteca.dao.IClienteDao;
import com.poli.biblioteca.dao.ILibrosDao;
import com.poli.biblioteca.dao.IPrestamoDao;
import com.poli.biblioteca.entity.Autor;
import com.poli.biblioteca.entity.Cliente;
import com.poli.biblioteca.entity.Libro;
import com.poli.biblioteca.entity.PrestamoLibro;

@Service
public class BibliotecaService {

	@Autowired
	private ILibrosDao librosDao;
	@Autowired
	private IAutoresDao autoresDao;
	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IPrestamoDao prestamoDao;
	
	public ResponseEntity<List<Libro>> getListaLibros(){
		List<Libro>  listaLibros = new ArrayList<>();
		try {			
			listaLibros = librosDao.findAll(); 
		}catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listaLibros,HttpStatus.OK);
	
	}
	
	public ResponseEntity<?> listarAutores(){
		return ResponseEntity.status(HttpStatus.OK).body(autoresDao.findAll());
	}
	
		public ResponseEntity<String> registrarAutor(Autor autor){
			try {
				 autoresDao.save(autor);
			} catch (Exception e) {
				return new ResponseEntity<String>("Hubo un error al insertar los datos.",HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Se ha guardado correctamente la información.",HttpStatus.OK);
		
	}
		
		public ResponseEntity<String> registarCliente(Cliente cliente){
			try {
				 clienteDao.save(cliente);
			} catch (Exception e) {
				return new ResponseEntity<String>("Hubo un error al insertar los datos.",HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Se ha guardado correctamente la información.",HttpStatus.OK);
		
	}
		
		public ResponseEntity<String> registarLibro(Libro libro){
			try {
				 Autor autor = null; 
				 if(libro.getAutor().getIdAutor() != null) {
					 autor = autoresDao.findById(libro.getAutor().getIdAutor()).orElse(null);
					 libro.setAutor(autor);
				 }				
				 librosDao.save(libro);
			} catch (Exception e) {
				return new ResponseEntity<String>("Hubo un error al insertar los datos.",HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("Se ha guardado correctamente la información.",HttpStatus.OK);
		
	}
		
		public ResponseEntity<String> solicitarPrestamo(int idLibro,Long idCliente){
			
			try {
				 PrestamoLibro prestamo = new PrestamoLibro();
				 Cliente cliente = clienteDao.findById(idCliente).orElse(null);
				 Libro libro = librosDao.findById(idLibro).orElse(null);
				 if(autorizarPrestamo(libro,cliente)) {
					 prestamo.setIdCliente(cliente);
					 prestamo.setFechaPrestamo(LocalDate.now());
					 prestamo.setFechaDevolucion(calcularFechaDevolucion(LocalDate.now()));
					 prestamoDao.save(prestamo);
				 }else {
					 return new ResponseEntity<String>("No hay stock del libro solicitado o el cliente tiene ya 5 prestamos,realiza una devolucion para poder"
					 		+ "solicitar otro libro.",HttpStatus.BAD_REQUEST);
				 }
				 
				 
			}catch (Exception e) {
				System.out.println(e);
				return new ResponseEntity<String>("Error al solicitar prestamo",HttpStatus.BAD_REQUEST);
			}
			
			
			return new ResponseEntity<String>("Prestamo autorizado",HttpStatus.OK);
			
			
		}
		
		
		public boolean autorizarPrestamo(Libro libro, Cliente cliente) {			
			boolean autorizado = true;
			if(cliente.getPrestamos() == null) {
					cliente.setPrestamos(Collections.emptyList());
			}
			
			if(cliente.getPrestamos().size() >= 5 || libro.getStock() == 0) {
				autorizado = false;
			}
			libro.setStock(libro.getStock() - 1);
					
			return autorizado;
		}

		public LocalDate calcularFechaDevolucion(LocalDate fecha) {
			int x = 1;
			while(x < 5 ) {						 
				 if(fecha.getDayOfWeek() == DayOfWeek.SATURDAY  || fecha.getDayOfWeek() == DayOfWeek.SUNDAY)   {
					 fecha =  fecha.plusDays(1);					 
				 }else {
					 fecha =  fecha.plusDays(1);					 
					 x++;
				 }
			 }
			fecha = fecha.getDayOfWeek() == DayOfWeek.SATURDAY ? fecha = fecha.plusDays(3): fecha;
			fecha = fecha.getDayOfWeek() == DayOfWeek.SUNDAY ? fecha = fecha.plusDays(2): fecha;
		
						
			return  fecha;
			
		}

		public ResponseEntity<List<Libro>> obtenerLibros(int idAutor) {
			Autor autor = null;
			List<Libro> libros = null;
			try {				  
				  autor = autoresDao.findById(idAutor).orElse(null); 				  
				  if(autor != null) {
					  libros = autor.getLibros();
				  }else {
					  
				  }
				  
			}catch (Exception e) {
				System.out.println(e);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return  new ResponseEntity<>(libros,HttpStatus.OK);
		}

		public ResponseEntity<Libro> getLibro(int idLibro) {
			Libro libro = null;
			try {
				libro = librosDao.findById(idLibro).orElse(null);
			}catch (Exception e) {
				System.out.println(e);
			}
			return new ResponseEntity<Libro>(libro,HttpStatus.OK);
		}

		public ResponseEntity<List<PrestamoLibro>> listarPrestamos() {
			List<PrestamoLibro> listaPrestamos = prestamoDao.findAll();				
			return new ResponseEntity<List<PrestamoLibro>>(listaPrestamos,HttpStatus.OK);
		}
		
		public ResponseEntity<String> eliminarPrestamo(int idPrestamo,int idLibro) {
			try {
				PrestamoLibro prestamo = prestamoDao.findById(idPrestamo).orElse(null);
				prestamoDao.delete(prestamo);			
				Libro libro = librosDao.findById(idLibro).orElse(null);
				libro.setStock(libro.getStock() + 1);
				librosDao.save(libro);
				return new ResponseEntity<String>("Se realizo la correcta devolucion",HttpStatus.OK);
				
				
			}catch (Exception e) {
				System.out.println(e);
				return new ResponseEntity<String>("Error al eliminar el prestamo",HttpStatus.BAD_REQUEST);
			}
		}

		public ResponseEntity<List<PrestamoLibro>> prestamosCliente(long idCliente) {
			Cliente cliente = clienteDao.findById(idCliente).orElse(null);
			List<PrestamoLibro> prestamos = cliente.getPrestamos();
			return new ResponseEntity<List<PrestamoLibro>>(prestamos,HttpStatus.OK);
		}
}
