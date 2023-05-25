package com.k1rard.restaurantesdata.dao;

import com.k1rard.restauranteentities.entity.Rol;
import com.k1rard.restauranteentities.entity.Rol;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KiraRD
 * Interface que realiza el CRUD de transacciones para la tabla de rol.
 */
public interface RolDAO {
	/**
	 * Metodo que permite guardar registros de Rol
	 * @param rol objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(Rol rol) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de Rol
	 * @param rol objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(Rol rol) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de Rol
	 * @param idRol identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idRol) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de Rol
	 * @return lista de Rol o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<Rol> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo Rol por identificador.
	 * @param idRol identificador del tipo.
	 * @return Rol consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	Rol consultarPorId(int idRol) throws SQLException, RestauranteExcepcion;
}
