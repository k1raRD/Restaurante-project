package com.k1rard.restaurantesdata.dao;

import com.k1rard.restauranteentities.entity.Sucursal;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KiraRD
 * Interface que realiza el CRUD de transacciones para la tabla de sucursal.
 */
public interface SucursalDAO {
	/**
	 * Metodo que permite guardar registros de Sucursal
	 * @param sucursal objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(Sucursal sucursal) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de Sucursal
	 * @param sucursal objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(Sucursal sucursal) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de Sucursal
	 * @param idSucursal identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idSucursal) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de Sucursal
	 * @return lista de Sucursal o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<Sucursal> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo Sucursal por identificador.
	 * @param idSucursal identificador del tipo.
	 * @return Sucursal consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	Sucursal consultarPorId(int idSucursal) throws SQLException, RestauranteExcepcion;
}
