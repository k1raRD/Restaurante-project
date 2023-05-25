/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.k1rard.restaurantesdata.dao;

import com.k1rard.restauranteentities.entity.Alimento;
import com.k1rard.restauranteentities.entity.Alimento;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author KiraRD
 * Interface que realiza el CRUD de transacciones para la tabla de Alimentos.
 */
public interface AlimentoDAO {
	/**
	 * Metodo que permite guardar registros de Alimentos
	 * @param alimento objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int guardar(Alimento alimento) throws SQLException;
	/**
	 * Metodo que permite actualizar registros de Alimentos
	 * @param alimento objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int actualizar(Alimento alimento) throws SQLException;
	/**
	 * Metodo que permite eliminar registros de Alimentos
	 * @param idAlimento identificador del tipo a eliminar.
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	int eliminar(int idAlimento) throws SQLException;
	/**
	 * Metodo que permite consultar los registros de Alimentos
	 * @return lista de Alimentos o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
	List<Alimento> consultar() throws SQLException;
	/**
	 * Metodo que permite consultar un registro de tipo Alimento por identificador.
	 * @param idAlimento identificador del tipo.
	 * @return Alimento consultado o null.
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 * @throws RestauranteExcepcion Excepcion personalizada para aclarar bien el mensaje al momento de hacer una sentencia SQL.
	 */
	Alimento consultarPorId(int idAlimento) throws SQLException, RestauranteExcepcion;
}
