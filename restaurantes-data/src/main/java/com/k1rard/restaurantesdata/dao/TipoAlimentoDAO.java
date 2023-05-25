/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.k1rard.restaurantesdata.dao;

import com.k1rard.restauranteentities.entity.TipoAlimento;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KiraRD
 * Interface que realiza el CRUD de transacciones para la tabla de Tipo de Alimentos.
 */
public interface TipoAlimentoDAO {
	/**
	 * Metodo que permite guardar registros de tipos de Alimentos
	 * @param tipoAlimento objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(TipoAlimento tipoAlimento) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de tipos de Alimentos
	 * @param tipoAlimento objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(TipoAlimento tipoAlimento) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de tipos de Alimentos
	 * @param idTipoAlimento identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idTipoAlimento) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de tipos de Alimento
	 * @return lista de tipos de Alimentos o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<TipoAlimento> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo Alimento por identificador.
	 * @param idTipoAlimento identificador del tipo.
	 * @return Tipo de Alimento consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	TipoAlimento consultarPorId(int idTipoAlimento) throws SQLException, RestauranteExcepcion;
}
