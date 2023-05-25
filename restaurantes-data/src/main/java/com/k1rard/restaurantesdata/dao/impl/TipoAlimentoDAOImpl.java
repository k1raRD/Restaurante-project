/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restaurantesdata.dao.impl;

import com.k1rard.restauranteentities.entity.TipoAlimento;
import com.k1rard.restaurantesdata.connection.ConnectionFactory;
import com.k1rard.restaurantesdata.dao.TipoAlimentoDAO;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KiraRD
 * Clase que implementa el CRUD de TipoAlimentoDAO
 */
public class TipoAlimentoDAOImpl implements TipoAlimentoDAO{

    @Override
    public int guardar(TipoAlimento tipoAlimento) throws SQLException {

        String sql = "INSERT INTO tipo_alimento(descripcion, fechaCreacion, estatus) VALUES('"+ tipoAlimento.getDescripcion() +"', '" + tipoAlimento.getFechaCreacion() + "', "+ tipoAlimento.isStatus() +");";

        int ejecutado = ConnectionFactory.ejecutarSQL(sql);

        return ejecutado;
    }

    @Override
    public int actualizar(TipoAlimento tipoAlimento) throws SQLException {
        String sql = "UPDATE tipo_alimento SET descripcion = '" + tipoAlimento.getDescripcion() + "', fechaModificacion = '" + tipoAlimento.getFechaModificacion() + "', estatus = "+ tipoAlimento.isStatus() +
                        " where idTipoAlimento = "+ tipoAlimento.getIdTipoAlimento() +";";

        int ejecutado = ConnectionFactory.ejecutarSQL(sql);

        return ejecutado;
    }

    @Override
    public int eliminar(int idTipoAlimento) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TipoAlimento> consultar() throws SQLException {
        String sql = "SELECT * FROM tipo_alimento ORDER BY descripcion;";
		
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

        List<TipoAlimento> tiposAlimentos = new ArrayList<TipoAlimento>();

        if(rs != null)
        {
                while (rs.next())
                {
                        TipoAlimento tipoAlimento = new TipoAlimento();
                        tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
                        tipoAlimento.setDescripcion(rs.getString("descripcion"));
                        tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                        tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
                        tipoAlimento.setStatus(rs.getBoolean("estatus"));

                        tiposAlimentos.add(tipoAlimento);
                }
        }

        return tiposAlimentos;
    }

    @Override
    public TipoAlimento consultarPorId(int idTipoAlimento) throws SQLException, RestauranteExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Metodo que permite consultar los tipos de alimentos que corresponden al restaurante del usuario en sesion
     * @param idRestauranteUsuarioSesion Identificador del restaurante
     * @return lista de alimentos 
     * @throws SQLException Excepcion en caso de error al ejecutar la 
     */
    public List<TipoAlimento> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
        String sql = "SELECT ta.* FROM tipo_alimento ta, restaurante_has_tipo_alimento rha, restaurante res "
                        + "WHERE ta.idTipoAlimento = rha.idTipoAlimento "
                        + "AND res.idRestaurante = rha.idRestaurante "
                        + "AND res.idRestaurante = " + idRestauranteUsuarioSesion +";";

        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

        List<TipoAlimento> tiposAlimentos = new ArrayList<>();

        if(rs != null) {
                while(rs.next()) {
                        TipoAlimento tipoAlimento = new TipoAlimento();
                        tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
                        tipoAlimento.setDescripcion(rs.getString("descripcion"));
                        tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                        tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
                        tipoAlimento.setStatus(rs.getBoolean("estatus"));

                        tiposAlimentos.add(tipoAlimento);
                }
        }
        return tiposAlimentos;
    }
    
    /**
     * Metodo que permite guardar un tipo de alimento en el restaurante del usuario en sesion.
     * @param tipoAlimento Objeto a guardar
     * @param idRestauranteUsuarioSesion Idntificador del restaurante
     * @return 1 en caso de ser guardado, 0 en caso de no guardado.
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL
     */
    public int guardar(TipoAlimento tipoAlimento, int idRestauranteUsuarioSesion) throws SQLException {
        String sql = "INSERT INTO tipo_alimento(descripcion, fechaCreacion, fechaModificacion, estatus)"
                      + " VALUES('"+ tipoAlimento.getDescripcion() +"', '" + tipoAlimento.getFechaCreacion() +"', '" + tipoAlimento.getFechaModificacion() +"', " + tipoAlimento.isStatus() +");";

        int ejecutado = ConnectionFactory.ejecutarSQL(sql);

        sql = "SELECT LAST_INSERT_ID();";

        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

        if(rs != null) {
                if(rs.next()) {
                        int lastIdTipoAlimento = rs.getInt(1);
                        sql = "INSERT INTO restaurante_has_tipo_alimento (idRestaurante, idTipoAlimento) VALUES (" + idRestauranteUsuarioSesion +", " + lastIdTipoAlimento +")";
                        ejecutado = ConnectionFactory.ejecutarSQL(sql);
                }
        }

        return ejecutado;
    }
    
    /**
     * Metodo que permite consultar los tipos de alimentos del menu del restaurante.
     * @param idMenu Identificador del menu
     * @return una lista de tipos de alimentos
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQK.
     */
    public List<TipoAlimento> consultarTiposAlimentosPorMenu(int idMenu) throws SQLException{
        String sql = "SELECT DISTINCT ta.* FROM tipo_alimento ta, menu m, alimento a " +
                    "WHERE ta.idTipoAlimento = a.idTipoAlimento " +
                    "AND a.idMenu = m.idMenu " +
                    "AND m.idMenu = " + idMenu +" " +
                    "ORDER BY ta.descripcion;";
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        List<TipoAlimento> tiposAlimentos = new ArrayList<>();
        
        if(rs != null){
            while(rs.next()){
                TipoAlimento tipoAlimento = new TipoAlimento();
                tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
                tipoAlimento.setDescripcion(rs.getString("descripcion"));
                tipoAlimento.setStatus(rs.getBoolean("estatus"));
                
                tiposAlimentos.add(tipoAlimento);
            }
        }
        
        return tiposAlimentos;
    }
}
