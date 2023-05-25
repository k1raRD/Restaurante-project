/**
 * 
 */
package com.k1rard.restaurantedata.dao;

import java.sql.SQLException;
import java.util.List;

import com.k1rard.restaurantedata.entity.TipoRestaurante;
import com.k1rard.restaurantedata.myexceptions.RestauranteExcepcion;

/**
 * @author KiraRD.
 * Interface que representa el CRUD de transaciones para la tabla de tipo_restaurante.
 */
public interface TipoRestauranteDAO {
	/**
	 * Metodo que permite guardar registros de tipos de restaurantes
	 * @param tipoRestaurante objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(TipoRestaurante tipoRestaurante) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de tipos de restaurantes
	 * @param tipoRestaurante objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(TipoRestaurante tipoRestaurante) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de tipos de restaurantes
	 * @param idTipoRestaurante identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idTipoRestaurante) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de tipos de restaurantes
	 * @return lista de tipos de restaurantes o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<TipoRestaurante> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo restaurante por identificador.
	 * @param idTipoRestaurante identificador del tipo.
	 * @return Tipo de restaurante consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	TipoRestaurante consultarPorId(int idTipoRestaurante) throws SQLException, RestauranteExcepcion;
}
