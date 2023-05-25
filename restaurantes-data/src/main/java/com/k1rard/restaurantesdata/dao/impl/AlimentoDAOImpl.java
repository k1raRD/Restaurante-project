/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restaurantesdata.dao.impl;

import com.k1rard.restauranteentities.entity.Alimento;
import com.k1rard.restauranteentities.entity.TipoAlimento;
import com.k1rard.restauranteentities.entity.TipoRestaurante;
import com.k1rard.restaurantesdata.connection.ConnectionFactory;
import com.k1rard.restaurantesdata.dao.AlimentoDAO;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KiraRD Clase que implementa el CRUD de transacciones para la tabla dealimentos.
 * 
 */
public class AlimentoDAOImpl implements AlimentoDAO {

    @Override
    public int guardar(Alimento alimento) throws SQLException {
        String sql = "INSERT INTO alimento(nombre, imagen, descuento, precio,descripcion, idTipoAlimento, fechaCreacion, fechaModificacion, estatus, idMenu, idRestaurante) "
					 + "VALUES('" + alimento.getNombre() +"', '" + alimento.getImagen() +"', " + alimento.getDescuento() +", " + alimento.getPrecio() +", '" + alimento.getDescripcion() + "',"
					 + " " + alimento.getTipoAlimento().getIdTipoAlimento()+ ", '"+ alimento.getFechaCreacion() +"', '" + alimento.getFechaModificacion() +"', " + alimento.isStatus()+","
					 + " " + alimento.getMenu().getIdMenu() + ", " + alimento.getRestaurante().getIdRestaurante() + ");";
		
	int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
	return ejecutado;
    }

    @Override
    public int actualizar(Alimento alimento) throws SQLException {
       String sql = "UPDATE alimento SET nombre = '" + alimento.getNombre() +"' , imagen = '" + alimento.getImagen() +"' , descripcion = '" + alimento.getDescripcion() +"' , idTipoAlimento = " + alimento.getTipoAlimento().getIdTipoAlimento() +", descuento = " + alimento.getDescuento() +" , precio = " + alimento.getPrecio()  +" , fechaModificacion = '" + alimento.getFechaModificacion()  +"' ,  estatus = " + alimento.isStatus() +" "
				+ "WHERE idAlimento = " + alimento.getIdAlimento() +" ;";
		
        int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
	return ejecutado;
    }

    @Override
    public int eliminar(int idAlimento) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Alimento> consultar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Alimento consultarPorId(int idAlimento) throws SQLException, RestauranteExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Metodo que permite consultar los Alimentos de Restaurante del usuario en sesion
     * @param idRestauranteUsuarioSesion identificador del Restaurante.
     * @return Regresa una lista de Alimentos.
     * @throws SQLException Excepcion generada en c asi de ERROR al ejecutar la sentencia SQL.
     */
    public List<Alimento> consultarPorIdRestaurante(int idRestauranteUsuarioSesion) throws SQLException {

        String sql = "SELECT a.*, ta.descripcion as descripcionTipo FROM alimento a, restaurante r, tipo_alimento ta, restaurante_has_tipo_alimento rha "
                + "WHERE a.idRestaurante = r.idRestaurante "
                + "AND a.idTipoAlimento = ta.idTipoAlimento "
                + "AND r.idRestaurante = rha.idRestaurante "
                + "AND ta.idTipoAlimento = rha.idTipoAlimento "
                + "AND r.idRestaurante = " + idRestauranteUsuarioSesion + ";";

        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

        List<Alimento> alimentos = new ArrayList<Alimento>();

        if (rs != null) {
            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setIdAlimento(rs.getInt("idAlimento"));
                alimento.setNombre(rs.getString("nombre"));
                alimento.setDescripcion(rs.getString("descripcion"));
                alimento.setImagen(rs.getString("imagen"));
                alimento.setPrecio(rs.getDouble("precio"));
                alimento.setDescuento(rs.getDouble("descuento"));
                alimento.setEstatus(rs.getBoolean("estatus"));
                alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null );
                
                TipoAlimento tipoAlimento = new TipoAlimento();
                tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
                tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
                
                alimento.setTipoAlimento(tipoAlimento);
                
                alimentos.add(alimento);
            }
        }

        return alimentos;
    }
    
    /**
     * Metodo que permite consultar la lista de alimentos disponibles del restaurante no asignado al menu
     * @param idRestauranteUsuarioSesion Identificador del restaurante
     * @return Lista de restaurantes diponibles
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Alimento> consultarAlimentosDisponibles(int idRestauranteUsuarioSesion) throws SQLException{
        List<Alimento> alimentosDB = new ArrayList<>();
        
        String sql = "SELECT a.*, ta.descripcion as descripcionTipo " +
                    "FROM alimento a, tipo_alimento ta, restaurante res " +
                    "WHERE a.idTipoAlimento = ta.idTipoAlimento " +
                    "AND a.idRestaurante = res.idRestaurante " +
                    "AND a.idMenu IS NULL " +
                    "AND a.idRestaurante = "+ idRestauranteUsuarioSesion +";";
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if (rs != null) {
            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setIdAlimento(rs.getInt("idAlimento"));
                alimento.setNombre(rs.getString("nombre"));
                alimento.setDescripcion(rs.getString("descripcion"));
                alimento.setImagen(rs.getString("imagen"));
                alimento.setPrecio(rs.getDouble("precio"));
                alimento.setDescuento(rs.getDouble("descuento"));
                alimento.setEstatus(rs.getBoolean("estatus"));
                alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null );

                TipoAlimento tipoAlimento = new TipoAlimento();
                tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
                tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));

                alimento.setTipoAlimento(tipoAlimento);

                alimentosDB.add(alimento);
            }
        }
        
        return alimentosDB;
    }
    
     /**
     * Metodo que permite consultar la lista de alimentos asignados al menu del restaurante
     * @param idMenu Identificador del menu del restaurante
     * @return Lista de alimentos asignados al menu
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Alimento> consultarAlimentosAsignados(Integer idMenu) throws SQLException{
        List<Alimento> alimentosDB = new ArrayList<>();
        
        String sql = "SELECT a.*, ta.descripcion AS descripcionTipo " +
                    "FROM alimento a, tipo_alimento ta, restaurante res " +
                    "WHERE a.idTipoAlimento = ta.idTipoAlimento " +
                    "AND a.idRestaurante = res.idRestaurante " +
                    "AND a.idMenu = " + idMenu +" ;";
        
        
                ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if (rs != null) {
            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setIdAlimento(rs.getInt("idAlimento"));
                alimento.setNombre(rs.getString("nombre"));
                alimento.setDescripcion(rs.getString("descripcion"));
                alimento.setImagen(rs.getString("imagen"));
                alimento.setPrecio(rs.getDouble("precio"));
                alimento.setDescuento(rs.getDouble("descuento"));
                alimento.setEstatus(rs.getBoolean("estatus"));
                alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null );

                TipoAlimento tipoAlimento = new TipoAlimento();
                tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
                tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));

                alimento.setTipoAlimento(tipoAlimento);

                alimentosDB.add(alimento);
            }
        }
        
        return alimentosDB;    
    }
    
    /**
     * Metodo que permite asignar un alimento al menu del restaurante
     * @param idAlimentoSeleccionado Identificador del alimento a asignar al menu
     * @param idMenu Identificador del menu
     * @return Regresa 1 en caso de asignado o 0 en caso de no ser asignado
     * @throws SQLException Excepcion en caso de error al ejecutar el SQL
     */
    public int asignarAlimentoMenu(int idAlimentoSeleccionado, int idMenu) throws SQLException{
        
        String sql = "UPDATE alimento SET idMenu = " + idMenu +" WHERE idAlimento = " + idAlimentoSeleccionado +" ;";
        
        int ejecutado = ConnectionFactory.ejecutarSQL(sql);
        
        return ejecutado;
    }

    /**
     * Metodo que permite quitar un alimento del menu del restaurante.
     * @param idAlimentoSeleccionado Identificador del alimento seleccionado
     * @return 1 en caso de ser removido o 0 en cado de no serlo
     * @throws SQLException Excepcion en caso de no ejecutar la sentencia SQL
     */
    public int quitarAlimentoMenu(int idAlimentoSeleccionado) throws SQLException{
        String sql = "UPDATE alimento SET idMenu = NULL WHERE idAlimento = " + idAlimentoSeleccionado +" ;";
        
        int ejecutado = ConnectionFactory.ejecutarSQL(sql);
        
        return ejecutado;
    }
    
    /**
     * Metodo que permite consultar los alimentos por tipo de alimento y su menu correspondiente
     * @param idTipoAlimento Identificador del tipo de alimento
     * @param idMenu Identificador del menu del restaurante
     * @return una lista de alimentos
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Alimento> consultarAlimentosPorTipoAndMenu(int idTipoAlimento, int idMenu) throws SQLException{
        String sql = "SELECT a.* FROM alimento a, tipo_alimento ta " +
                    "WHERE a.idTipoAlimento = ta.idTipoAlimento " +
                    "AND a.idTipoAlimento = " + idTipoAlimento +" " +
                    "AND idMenu = " + idMenu +";";
        
        List<Alimento> alimentosDB = new ArrayList<>();
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if(rs != null){
            while(rs.next()){
                Alimento alimento = new Alimento();
                alimento.setIdAlimento(rs.getInt("idAlimento"));
                alimento.setNombre(rs.getString("nombre"));
                alimento.setImagen(rs.getString("imagen"));
                alimento.setDescripcion(rs.getString("descripcion"));
                alimento.setPrecio(rs.getDouble("precio"));
                alimento.setDescuento(rs.getDouble("descuento"));
                
                alimentosDB.add(alimento);
            }
        }
        
        return alimentosDB;
    }
}
