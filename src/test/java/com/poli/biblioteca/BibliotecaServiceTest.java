package com.poli.biblioteca;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import com.poli.biblioteca.entity.Cliente;
import com.poli.biblioteca.entity.Libro;
import com.poli.biblioteca.entity.PrestamoLibro;
import com.poli.biblioteca.service.BibliotecaService;


class BibliotecaServiceTest {
	
	private BibliotecaService bibliotecaService;
	
	private Libro libro;
	private Libro libro2;
	private Libro libro3;
	private Cliente cliente;
	private Cliente cliente2;

	@BeforeEach
	public void setup() {
		bibliotecaService = new BibliotecaService();		
		libro = new Libro("El retrato de dorian gray", "Gotico", "1890", 2);
		libro2 = new Libro("El diablo en la botella", "Ficcion", "1891", 0);
		libro3 = new Libro("El retrato de dorian gray", "Gotico", "1890", 5);
		libro.setIdLibro(1);
		libro2.setIdLibro(2);
		libro3.setIdLibro(3);
		cliente = new Cliente("carlos", "merchan", "dik@gmail.com", 226);
		cliente2 = new Cliente("luis", "tabarez", "ddk@gmail.com", 45);
		cliente.setIdCliente(1);
		cliente2.setIdCliente(2);
		List<PrestamoLibro> prestamos = new ArrayList<>();
		prestamos.add(new PrestamoLibro());
		prestamos.add(new PrestamoLibro());
		prestamos.add(new PrestamoLibro());
		prestamos.add(new PrestamoLibro());
		prestamos.add(new PrestamoLibro());
		cliente2.setPrestamos(prestamos);
		List<PrestamoLibro> prestamo = new ArrayList<>();
		prestamo.add(new PrestamoLibro());
		cliente.setPrestamos(prestamo);

	}

	@Test
	void fechaDevolucionTest() {
		LocalDate fechaObtenida = bibliotecaService.calcularFechaDevolucion(LocalDate.of(2022, 9, 24));
		LocalDate fechaEsperada = LocalDate.of(2022, 9, 30);
		assertEquals(fechaEsperada, fechaObtenida);

		LocalDate fechaObtenida1 = bibliotecaService.calcularFechaDevolucion(LocalDate.of(2022, 10, 6));
		LocalDate fechaEsperada1 = LocalDate.of(2022, 10, 12);
		assertEquals(fechaEsperada1, fechaObtenida1);

		LocalDate fechaObtenida2 = bibliotecaService.calcularFechaDevolucion(LocalDate.of(2022, 10, 18));
		LocalDate fechaEsperada2 = LocalDate.of(2022, 10, 25);
		assertEquals(fechaEsperada2, fechaObtenida2);

	}


	@Test
	void autorizarPrestamoTest() {
		
		boolean autorizar = bibliotecaService.autorizarPrestamo(libro, cliente);
		
		assertEquals(true, autorizar);

	}
	
	@Test
	void autorizarPrestamoTestFail() {
		
		boolean autorizar = bibliotecaService.autorizarPrestamo(libro2, cliente);		
		assertEquals(false, autorizar);
		
		
		boolean autorizar1 = bibliotecaService.autorizarPrestamo(libro3, cliente2);		
		assertEquals(false, autorizar1);

	}

}
