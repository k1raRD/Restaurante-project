/**
 * 
 */
package com.k1rard.restaurantesdata.dao;

import com.k1rard.restauranteentities.entity.Empleado;
import java.sql.SQLException;
import java.util.List;

import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;

/**
 * @author KiraRD
 * Interface proprociona CRUD para las transacciones hacia las bases de datos
 */
public interface EmpleadoDAO {
	
	/**
	 * Metodo que permite guardar registros de tipos de empleados
	 * @param empleado objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(Empleado empleado) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de tipos de empleados
	 * @param empleado objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(Empleado empleado) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de tipos de empleados
	 * @param idEmpleado identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idEmpleado) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de tipos de empleados
	 * @return lista de tipos de empleados o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<Empleado> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo Empleado por identificador.
	 * @param idEmpleado identificador del tipo.
	 * @return Tipo de Empleado consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	Empleado consultarPorId(int idEmpleado) throws SQLException, RestauranteExcepcion;
}
