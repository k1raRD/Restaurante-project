/**
 * 
 */
package com.k1rard.restaurantedata.dao;

import java.sql.SQLException;
import java.util.List;

import com.k1rard.restaurantedata.entity.Restaurante;
import com.k1rard.restaurantedata.myexceptions.RestauranteExcepcion;

/**
 * @author KiraRD
 * Interface proprociona CRUD para las transacciones hacia las bases de datos
 */
public interface RestauranteDAO {
	
	/**
	 * Metodo que permite guardar registros de tipos de restaurantes
	 * @param Restaurante objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(Restaurante restaurante) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de tipos de restaurantes
	 * @param restaurante objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(Restaurante restaurante) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de tipos de restaurantes
	 * @param idRestaurante identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idRestaurante) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de tipos de restaurantes
	 * @return lista de tipos de restaurantes o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<Restaurante> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo restaurante por identificador.
	 * @param idRestaurante identificador del tipo.
	 * @return Tipo de restaurante consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	Restaurante consultarPorId(int idRestaurante) throws SQLException, RestauranteExcepcion;
}
