/**
 * 
 */
package com.k1rard.restaurantedata.dao.impl.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.k1rard.restaurantedata.dao.impl.RestauranteDAOImpl;
import com.k1rard.restaurantedata.entity.Menu;
import com.k1rard.restaurantedata.entity.Restaurante;
import com.k1rard.restaurantedata.entity.TipoRestaurante;

/**
 * @author KiraRD
 *
 */
class RestauranteDAOImplTest {

	@Test
	void testGuardarExitoso() {
		
		RestauranteDAOImpl restauranteDaoImpl = new RestauranteDAOImpl();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante pica pollo");
		restaurante.setImagen("HuangTong.png");
		restaurante.setStatus(true);
		restaurante.setFechaCreacion(LocalDateTime.now());
		
		TipoRestaurante tipoRestaurante = new TipoRestaurante();
		tipoRestaurante.setIdTipoRestaurante(27);
		restaurante.setTipoRestaurante(tipoRestaurante);
		
		Menu menu = new Menu();
		menu.setIdMenu(4);
		
		restaurante.setMenu(menu);
		
		int guardado = 0;
		
		try {
			// Primer caso si el guardado exitoso.
			guardado = restauranteDaoImpl.guardar(restaurante);
			assertTrue(guardado > 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGuardarErrorSQL() {
		
		RestauranteDAOImpl restauranteDaoImpl = new RestauranteDAOImpl();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Restaurante Hiroshima");
		restaurante.setImagen("Hiroshimares.png");
		restaurante.setStatus(true);
		restaurante.setFechaCreacion(LocalDateTime.now());
		
		TipoRestaurante tipoRestaurante = new TipoRestaurante();
		tipoRestaurante.setIdTipoRestaurante(18);
		restaurante.setTipoRestaurante(tipoRestaurante);
		
		assertTrue(restaurante.getTipoRestaurante().getIdTipoRestaurante() == 18);
		
		Menu menu = new Menu();
		menu.setIdMenu(4);
		restaurante.setMenu(menu);
		
		assertTrue(restaurante.getMenu().getIdMenu() == 4);
		
		int guardado = 0;
		
		try {
			// Primer caso si guardado > 0.
			guardado = restauranteDaoImpl.guardar(restaurante);
			
			assertTrue(guardado > 0);
			
			System.out.println("Se guardo exitosamente.");
			
		} catch (SQLException e) {
			// Segundo caso: Exitoso si no se guardo el restaurante porque hubo un error.
			assertTrue(guardado == 0);
			System.err.println("No se guardo.");
			e.printStackTrace();
		}
	}
	
	@Test
	void actualizarExitoso()
	{
		RestauranteDAOImpl restauranteDaoImpl = new RestauranteDAOImpl();
		
		Restaurante restaurante = new Restaurante();
		restaurante.setIdRestaurante(23);
		restaurante.setNombre("Restaurante Tokyo");
		restaurante.setImagen("Tokyores.png");
		restaurante.setStatus(true);
		restaurante.setFechaModificacion(LocalDateTime.now());
		
		TipoRestaurante tipoRestaurante = new TipoRestaurante();
		tipoRestaurante.setIdTipoRestaurante(18);
		restaurante.setTipoRestaurante(tipoRestaurante);
		
		assertTrue(restaurante.getTipoRestaurante().getIdTipoRestaurante() == 18);
		
		Menu menu = new Menu();
		menu.setIdMenu(4);
		restaurante.setMenu(menu);
		
		assertTrue(restaurante.getMenu().getIdMenu() == 4);
		
		int actualizado = 0;
		
		try {
			// Primer caso si guardado > 0.
			actualizado = restauranteDaoImpl.actualizar(restaurante);
			assertTrue(actualizado > 0);
			System.out.println("Se ha actualizado exitosamente.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testEliminarExitoso()
	{
		int idRestaurante = 26;
		int eliminado = 0;
	
		RestauranteDAOImpl restauranteDaoImpl = new RestauranteDAOImpl();
		
		try {
			eliminado = restauranteDaoImpl.eliminar(idRestaurante);
			assertTrue(eliminado > 0);
			System.out.println("Se ha eliminado el registro exitosamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void consultarExitoso() 
	{
		RestauranteDAOImpl restauranteDaoImpl = new RestauranteDAOImpl();
		
		try {
			List<Restaurante> restaurantesConsultados = restauranteDaoImpl.consultar();
			
			assertTrue(restaurantesConsultados.size() > 0);
			
//			
//			for(Restaurante restaurante: restaurantesConsultados)
//			{
//				System.out.println("Restaurante: " + restaurante.getNombre());
//			}
			
			restaurantesConsultados.forEach(restaurante -> {
				System.out.println("Restaurante: " + restaurante.getNombre());
			});
			
			System.out.println("La peticion ha sido exitosa");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
