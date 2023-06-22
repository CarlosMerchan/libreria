package com.poli.biblioteca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.poli.biblioteca.Utils.Datos;
import com.poli.biblioteca.dao.IAutoresDao;
import com.poli.biblioteca.dao.IClienteDao;
import com.poli.biblioteca.dao.ILibrosDao;
import com.poli.biblioteca.dao.IPrestamoDao;
import com.poli.biblioteca.entity.Cliente;
import com.poli.biblioteca.entity.Libro;
import com.poli.biblioteca.entity.PrestamoLibro;
import com.poli.biblioteca.service.BibliotecaService;

@SpringBootTest
class BibliotecaServiceTest {

	@MockBean
	private ILibrosDao librosDao;
	@MockBean
	private IAutoresDao autoresDao;
	@MockBean
	private IClienteDao clienteDao;
	@MockBean
	private IPrestamoDao prestamoDao;

	@Autowired
	private BibliotecaService bibliotecaService;
	
	private Cliente cliente1;
	private Cliente cliente2;
	private Libro libro1;
	private Libro libro2;
	private Libro libro3;
	

	@BeforeEach
	public void setup() {
		 cliente1 = Datos.crearCliente1().orElse(null);
		 cliente1.setPrestamos(Collections.emptyList());
		 cliente2 = Datos.crearCliente1().orElse(null);
		 cliente2.setPrestamos(Collections.emptyList());
		 libro1 = Datos.crearLibro1().orElse(null);
		 libro2 = Datos.crearLibro2().orElse(null);
		 libro3 = Datos.crearLibro3().orElse(null);
			
	}

	@Test
	void fechaDevolucionTest() {
		LocalDate fechaObtenida = bibliotecaService.calcularFechaDevolucion(LocalDate.of(2022, 9, 24));
		LocalDate fechaEsperada = calcularFechaDevolucion(LocalDate.of(2022, 9, 24));
		assertEquals(fechaEsperada, fechaObtenida);

		LocalDate fechaObtenida1 = bibliotecaService.calcularFechaDevolucion(LocalDate.of(2022, 10, 6));
		LocalDate fechaEsperada1 = calcularFechaDevolucion(LocalDate.of(2022, 10, 6));
		assertEquals(fechaEsperada1, fechaObtenida1);

		LocalDate fechaObtenida2 = bibliotecaService.calcularFechaDevolucion(LocalDate.of(2022, 10, 18));
		LocalDate fechaEsperada2 = calcularFechaDevolucion(LocalDate.of(2022, 10, 18));
		assertEquals(fechaEsperada2, fechaObtenida2);

	}

	@Test
	void autorizarPrestamoTest() {		
		
		boolean autorizar = bibliotecaService.autorizarPrestamo(libro1, cliente1);

		assertEquals(true, autorizar);		

	}

	@Test
	void autorizarPrestamoTestFail() {	
		
		boolean autorizar1 = bibliotecaService.autorizarPrestamo(libro1,cliente2);
		boolean autorizar2 = bibliotecaService.autorizarPrestamo(libro2,cliente2);
		assertEquals(true, autorizar1);
		assertEquals(false, autorizar2);

		

	}
	
	
	@Test
	void listarLibros() {
		when(librosDao.findAll()).thenReturn(Arrays.asList(libro1,libro2,libro3));
		
		List<Libro> response = bibliotecaService.getListaLibros().getBody();
		
		assertTrue(() -> response.size() != 0);
		assertEquals(3, response.size());
		assertEquals("Gotico", libro1.getGenero());
		
		verify(librosDao,atLeastOnce()).findAll();
	}

	public LocalDate calcularFechaDevolucion(LocalDate fecha) {
		int x = 1;
		while (x < 5) {
			if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
				fecha = fecha.plusDays(1);
			} else {
				fecha = fecha.plusDays(1);
				x++;
			}
		}
		fecha = fecha.getDayOfWeek() == DayOfWeek.SATURDAY ? fecha = fecha.plusDays(3) : fecha;
		fecha = fecha.getDayOfWeek() == DayOfWeek.SUNDAY ? fecha = fecha.plusDays(2) : fecha;

		return fecha;

	}
	
	
	

}
