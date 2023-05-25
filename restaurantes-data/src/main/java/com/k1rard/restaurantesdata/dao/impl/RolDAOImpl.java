package com.k1rard.restaurantesdata.dao.impl;

import com.k1rard.restauranteentities.entity.Rol;
import com.k1rard.restaurantesdata.connection.ConnectionFactory;
import com.k1rard.restaurantesdata.dao.RolDAO;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author k1rard
 * Clase que implementa el CRUD de transaccion de la interface de RolDAO
 */
public class RolDAOImpl implements RolDAO {

    @Override
    public int guardar(Rol rol) throws SQLException {
        String sql = "INSERT INTO rol (nombre, fechaCreacion, fechaModificacion,estatus) "
                     + "VALUES ('"+ rol.getNombre() +"', '" + rol.getFechaCreacion() +"', '" + rol.getFechaModificacion() +"' ," + rol.isStatus() +");";
        
        int guardado = ConnectionFactory.ejecutarSQL(sql);
        
        return guardado;
    }

    @Override
    public int actualizar(Rol rol) throws SQLException {
        String sql = "UPDATE rol SET "
                     + "nombre = '" + rol.getNombre() +"', fechaModificacion = '" + rol.getFechaModificacion() +"', estatus = " + rol.isStatus() +" WHERE idRol = " + rol.getIdRol() +";";
        
        int actualizado = ConnectionFactory.ejecutarSQL(sql);
        
        return actualizado;
    }

    @Override
    public int eliminar(int idRol) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Rol> consultar() throws SQLException {
                List<Rol> rolesDB = new ArrayList<>();
        
        String sql = "SELECT * FROM rol ORDER BY nombre ;";
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if(rs != null){
            while(rs.next()){
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setNombre(rs.getString("nombre"));
                rol.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                rol.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
                rol.setStatus(rs.getBoolean("estatus"));
                
                rolesDB.add(rol);
            }
        }
        
        return rolesDB;
    }

    @Override
    public Rol consultarPorId(int idRol) throws SQLException, RestauranteExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Metodo que permite consultar los roles para el administrador de sucursal que desee guardar un nuevo empleado
     * @return la lista de roles disponibles
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Rol> consultarRolesSinAdminGeneral() throws SQLException{
        List<Rol> rolesDB = new ArrayList<>();
        
        String sql = "SELECT * FROM rol " +
                      "WHERE idRol != 5;";
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if(rs != null){
            while(rs.next()){
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setNombre(rs.getString("nombre"));
                rol.setStatus(rs.getBoolean("estatus"));
                
                rolesDB.add(rol);
            }
        }
        
        return rolesDB;
    }
    
}
