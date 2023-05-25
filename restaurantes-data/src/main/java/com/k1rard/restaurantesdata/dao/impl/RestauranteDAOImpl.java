/**
 * 
 */
package com.k1rard.restaurantesdata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.k1rard.restauranteentities.entity.Menu;
import com.k1rard.restauranteentities.entity.Restaurante;
import com.k1rard.restauranteentities.entity.TipoRestaurante;
import com.k1rard.restaurantesdata.connection.ConnectionFactory;
import com.k1rard.restaurantesdata.dao.RestauranteDAO;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;

/**
 * @author KiraRD Clase que implementa los metodos de CRUD de la interface
 *         Restaurant
 */
public class RestauranteDAOImpl implements RestauranteDAO {

	static {
		try {
			ConnectionFactory.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.err.println("Error de Base de datos: " + e.getMessage());
		}
	}

	@Override
	public int guardar(Restaurante restaurante) throws SQLException {

		String sql = "INSERT INTO restaurante(nombre , imagen, slogan, idTipoRestaurante, fechaCreacion, estatus, idMenu)"
				+ "VALUES('" + restaurante.getNombre() + "', '" + restaurante.getImagen() + "', '', "
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + ", '" + restaurante.getFechaCreacion() + "', "
				+ restaurante.isStatus() + ", " + restaurante.getMenu().getIdMenu() + ");";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int actualizar(Restaurante restaurante) throws SQLException {

		String sql = "UPDATE restaurante SET nombre= '" + restaurante.getNombre() + "', imagen= '"
				+ restaurante.getImagen() + "', slogan= '" + restaurante.getSlogan() + "', idTipoRestaurante= "
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + ", fechaModificacion= '" + restaurante.getFechaModificacion()
				+ "', estatus= " + restaurante.isStatus() + ", idMenu= " + restaurante.getMenu().getIdMenu()
				+ " WHERE idRestaurante = " + restaurante.getIdRestaurante() + ";";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public int eliminar(int idRestaurante) throws SQLException {
		
		String sql = "DELETE FROM restaurante WHERE idRestaurante = " + idRestaurante +";";

		int ejecutado = ConnectionFactory.ejecutarSQL(sql);

		return ejecutado;
	}

	@Override
	public List<Restaurante> consultar() throws SQLException {
		String sql = "SELECT r.*, tp.descripcion FROM restaurante r, tipo_restaurante tp WHERE r.idTipoRestaurante = tp.idTipoRestaurante ORDER BY nombre;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		
		if(rs != null)
		{
			while (rs.next())
			{
				Restaurante restaurante = new Restaurante();
                                restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setImagen(rs.getString("imagen"));
				restaurante.setStatus(rs.getBoolean("estatus"));
				restaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				restaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion").toLocalDateTime() != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
				restaurante.setSlogan(rs.getString("slogan"));
                                
                                TipoRestaurante tipoRestaurante = new TipoRestaurante();
                                tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
                                tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				
                                restaurante.setTipoRestaurante(tipoRestaurante);
                                
				restaurantes.add(restaurante);
			}
		}
		
		return restaurantes;
	}

	@Override
	public Restaurante consultarPorId(int idRestaurante) throws SQLException, RestauranteExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
