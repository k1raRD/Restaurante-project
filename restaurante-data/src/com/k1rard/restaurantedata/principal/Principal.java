/**
 * 
 */
package com.k1rard.restaurantedata.principal;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.k1rard.restaurantedata.dao.impl.TipoRestauranteDAOImpl;
import com.k1rard.restaurantedata.entity.TipoRestaurante;
import com.k1rard.restaurantedata.myexceptions.RestauranteExcepcion;

/**
 * @author KiraRD.
 * Clase principal para probar la conexion a la base de datos
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TipoRestauranteDAOImpl tipoRestauranteDAOImpl = new TipoRestauranteDAOImpl();
		
		// :::::::::::::::::::: Prueba de INSERT ::::::::::::::::::::
		
//		TipoRestaurante tipoRestaurante = new TipoRestaurante();
//		tipoRestaurante.setDescripcion("Griego");
//		tipoRestaurante.setStatus(true);
//		tipoRestaurante.setFechaCreacion(LocalDateTime.now());
		
		
//		try {
//			int guardado = tipoRestauranteDAOImpl.guardar(tipoRestaurante);
//			
//			// Si el tipo de restaurante se guardo en la base de datos.
//			if(guardado > 0)
//			{
//				System.out.println("El tipo de restaurante " + tipoRestaurante.getDescripcion() + " fue guardado exitosamente.");
//			}
//			else
//			{
//				System.err.println("Hubo un error al guardar el tipo de restaurante");
//			}
//		} catch (SQLException e){
//			System.err.println("Error: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		// :::::::::::::::::::::::: PRUEBA DE UPDATE ::::::::::::::::::::::::::::
		
//		TipoRestaurante tipoRestaurante = new TipoRestaurante();
//		tipoRestaurante.setIdTipoRestaurante(22);
//		tipoRestaurante.setDescripcion("Griego");
//		tipoRestaurante.setStatus(false);
//		tipoRestaurante.setFechaModificacion(LocalDateTime.now());
//		
//		
//		try {
//			int guardado = tipoRestauranteDAOImpl.actualizar(tipoRestaurante);
//			
//			// Si el tipo de restaurante se actualizo en la base de datos.
//			if(guardado > 0)
//			{
//				System.out.println("El tipo de restaurante " + tipoRestaurante.getDescripcion() + " fue Actualizado exitosamente.");
//			}
//			else
//			{
//				System.err.println("Hubo un error al Actualizar el tipo de restaurante");
//			}
//		} catch (SQLException e){
//			System.err.println("Error: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		// ::::::::::::::::::::::::: Prueba de DELETE ::::::::::::::::::::::::::::::::
		
//		TipoRestaurante tipoRestaurante = new TipoRestaurante();
//		tipoRestaurante.setIdTipoRestaurante(22);
//		tipoRestaurante.setDescripcion("Griego");
//		
//		try {
//			int eliminado = tipoRestauranteDAOImpl.eliminar(28);
//			
//			// Si el tipo de restaurante se elimino en la base de datos.
//			if(eliminado > 0)
//			{
//				System.out.println("El tipo de restaurante " + tipoRestaurante.getDescripcion() + " eliminado exitosamente.");
//			}
//			else
//			{
//				System.err.println("Hubo un error al eliminar el tipo de restaurante");
//			}
//		} catch (SQLException e){
//			System.err.println("Error: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		
		// :::::::::::::::::::::: Prueba de SELECT ::::::::::::::::::::::::::::::::::::
		
//		try {
//			List<TipoRestaurante> tiposConsultados =  tipoRestauranteDAOImpl.consultar();
//			
//			for (TipoRestaurante tipoRestaurante : tiposConsultados) {
//				System.out.println("ID: " + tipoRestaurante.getIdTipoRestaurante());
//				System.out.println("DESCRIPCION: " + tipoRestaurante.getDescripcion() );
//				System.out.println("ESTATUS: " + tipoRestaurante.isStatus() + "\n");
//				
//			}
//		} catch (SQLException e){
//			System.err.println("Error: " + e.getMessage());
//			e.printStackTrace();
//		}
		
		// ::::::::::::::::::::::::::::::::::: Prueba De SELECT WHERE :::::::::::::::::::::::::::::::::::
		
		try {
			
			TipoRestaurante tipoConsultado =  tipoRestauranteDAOImpl.consultarPorId(16);
			
			if(tipoConsultado != null)
			{
				System.out.println("ID: " + tipoConsultado.getIdTipoRestaurante());
				System.out.println("DESCRIPCION: " + tipoConsultado.getDescripcion() );
				System.out.println("ESTATUS: " + tipoConsultado.isStatus() + "\n");
			}
			else
			{
				System.out.println("No se encontro el tipo de restaurante que buscas.");
			}
		} catch (SQLException e){
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (RestauranteExcepcion e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}

}
