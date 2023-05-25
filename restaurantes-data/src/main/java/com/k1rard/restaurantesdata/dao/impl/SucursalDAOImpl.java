/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restaurantesdata.dao.impl;

import com.k1rard.restauranteentities.entity.Restaurante;
import com.k1rard.restauranteentities.entity.Sucursal;
import com.k1rard.restaurantesdata.connection.ConnectionFactory;
import com.k1rard.restaurantesdata.dao.SucursalDAO;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author k1rard
 * Clase que implementa el CRUD de la interface SucursalDAO
 */
public class SucursalDAOImpl implements SucursalDAO {

    @Override
    public int guardar(Sucursal sucursal) throws SQLException {
        String sql = "INSERT INTO sucursal (nombre, idRestaurante, fechaCreacion, fechaModificacion, estatus) " +
                     "VALUES ('" + sucursal.getNombre() +"', " + sucursal.getRestaurante().getIdRestaurante() +", '"+ sucursal.getFechaCreacion() +"', '" + sucursal.getFechaModificacion() +"', " + sucursal.isStatus() +") ;";
        
        int guardado = ConnectionFactory.ejecutarSQL(sql);
        
        return guardado;
    }

    @Override
    public int actualizar(Sucursal sucursal) throws SQLException {
        String sql = "UPDATE sucursal SET nombre = '" +  sucursal.getNombre() + "', fechaModificacion = '" + sucursal.getFechaModificacion() +"', estatus = " + sucursal.isStatus() +" "
                             + "WHERE idSucursal = " + sucursal.getIdSucursal() +" ;";
        
        int actualizado = ConnectionFactory.ejecutarSQL(sql);
        
        return actualizado;
    }

    @Override
    public int eliminar(int idSucursal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Sucursal> consultar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Sucursal consultarPorId(int idSucursal) throws SQLException, RestauranteExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Metodo que permite consultar la lista de sucursales de un restaurante.
     * @param idRestauranteUsuarioSesion Identificador del restaurante.
     * @return devuelve una lista de sucursales.
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Sucursal> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
        List<Sucursal> sucursalesDB = new ArrayList<>();
        
        String sql = "SELECT s.*, res.nombre as nombreRestaurante "
                + "FROM sucursal s, restaurante res WHERE s.idRestaurante = res.idRestaurante "
                + "AND s.idRestaurante = " + idRestauranteUsuarioSesion +";";
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if(rs != null){
            while(rs.next()){
                Sucursal sucursal = new Sucursal();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                sucursal.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
                sucursal.setStatus(rs.getBoolean("estatus"));
                
                Restaurante restaurante = new Restaurante();
                restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
                restaurante.setNombre(rs.getString("nombreRestaurante"));
                sucursal.setRestaurante(restaurante);
                
                sucursalesDB.add(sucursal);
                
            }
        }
        
        return sucursalesDB;
    }
}
