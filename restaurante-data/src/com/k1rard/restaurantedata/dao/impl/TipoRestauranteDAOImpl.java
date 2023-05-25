/**
 * 
 */
package com.k1rard.restaurantedata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.k1rard.restaurantedata.connection.ConnectionFactory;
import com.k1rard.restaurantedata.dao.TipoRestauranteDAO;
import com.k1rard.restaurantedata.entity.TipoRestaurante;
import com.k1rard.restaurantedata.enums.CodigoEnum;
import com.k1rard.restaurantedata.myexceptions.RestauranteExcepcion;

/**
 * @author KiraRD.
 * Clase que implementa el CRUD y otros metodos personalizados de transaciones a la base de datos 
 * en la tabla tipo_restaurante
 */
public class TipoRestauranteDAOImpl implements TipoRestauranteDAO{

	static {
		try {
			ConnectionFactory.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.err.println("Error de Base de datos: " + e.getMessage());
		}
	}
	
	@Override
	public int guardar(TipoRestaurante tipoRestaurante) throws SQLException {
		
		String sql = "INSERT INTO tipo_restaurante(descripcion, fechaCreacion, estatus) VALUES('"+ tipoRestaurante.getDescripcion() +"', '" + tipoRestaurante.getFechaCreacion() + "', "+ tipoRestaurante.isStatus() +");";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int actualizar(TipoRestaurante tipoRestaurante) throws SQLException {
		String sql = "UPDATE tipo_restaurante SET descripcion = '" + tipoRestaurante.getDescripcion() + "', fechaModificacion = '" + tipoRestaurante.getFechaModificacion() + "', estatus = "+ tipoRestaurante.isStatus() +
				" where idTipoRESTAURANTE = "+ tipoRestaurante.getIdTipoRestaurante() +";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int eliminar(int idTipoRestaurante) throws SQLException {
		
		String sql = "DELETE FROM tipo_restaurante WHERE idTipoRESTAURANTE = "+ idTipoRestaurante +";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public List<TipoRestaurante> consultar() throws SQLException {
		
		String sql = "SELECT * FROM tipo_restaurante ORDER BY descripcion;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		List<TipoRestaurante> tiposRestaurantes = new ArrayList<TipoRestaurante>();
		
		if(rs != null)
		{
			while (rs.next())
			{
				TipoRestaurante tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				tipoRestaurante.setStatus(rs.getBoolean("estatus"));
				
				tiposRestaurantes.add(tipoRestaurante);
			}
		}
		
		return tiposRestaurantes;
	}

	@Override
	public TipoRestaurante consultarPorId(int idTipoRestaurante) throws SQLException, RestauranteExcepcion {
		String sql = "SELECT * ROM tipo_restaurante WHERE idTipoRestaurante = "+ idTipoRestaurante +";";
		
		ResultSet rs = null;
		
		try {
			rs = ConnectionFactory.ejecutarSQLSelect(sql);
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
			if(e.getClass().getName().contains("SQLSyntaxErrorException"))
			{
				throw new RestauranteExcepcion("ERROR de Sysntaxis en la sentencia " + sql, CodigoEnum.SINTAXIS_SQL_ERROR_CODE);
			}
			
		}
		
		TipoRestaurante tipoRestaurante = null;
		
		if(rs != null)
		{
			if(rs.next())
			{
				tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				tipoRestaurante.setStatus(rs.getBoolean("estatus"));
			}
				
		}
		
		return tipoRestaurante;
		
	}

}
