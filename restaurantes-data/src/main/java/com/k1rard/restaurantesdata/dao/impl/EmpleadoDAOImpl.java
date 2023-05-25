/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restaurantesdata.dao.impl;

import com.k1rard.restauranteentities.entity.Empleado;
import com.k1rard.restauranteentities.entity.Menu;
import com.k1rard.restauranteentities.entity.Restaurante;
import com.k1rard.restaurantesdata.connection.ConnectionFactory;
import com.k1rard.restaurantesdata.dao.EmpleadoDAO;
import com.k1rard.restaurantesdata.myexceptions.RestauranteExcepcion;
import java.sql.SQLException;
import java.util.List;
import com.k1rard.restauranteentities.entity.Rol;
import com.k1rard.restauranteentities.entity.Sucursal;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author KiraRD Clase que implementa la funcionalidad del CRUD de empleados.
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {

    static {
        try {
            ConnectionFactory.conectar();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

     /**
     * Metodo que permite guardar un empleado
     * @param empleado objeto a guardar
     * @return 1 en caso de ser removido o 0 en cado de no serlo
     * @throws SQLException Excepciion en caso de error al ejecutar la sentencia SQL
     */
    @Override
    public int guardar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleado " +
                "(nombre, primerApellido, segundoApellido, idRol, idSucursal, usuario, password, email, superadmin, fechaCreacion,fechaModificacion, estatus) " +
                "VALUES('" + empleado.getNombre() + "','" + empleado.getPrimerApellido()+ "','" + empleado.getSegundoApellido()+ "', " + empleado.getRol().getIdRol()+ ","
                + "" + empleado.getSucursal().getIdSucursal()+ ", '" + empleado.getUsuario()+ "', '" + empleado.getPassword()+ "', '" + empleado.getEmail() + "'," + empleado.isSuperAdmin()+ ","
                + "'" + empleado.getFechaCreacion()+ "', '" + empleado.getFechaModificacion()+ "', " + empleado.isStatus()+ ");";

        int guardado = ConnectionFactory.ejecutarSQL(sql);

        return guardado;
    }

    @Override
    public int actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleado SET nombre = '" + empleado.getNombre() +"', primerApellido = '" + empleado.getPrimerApellido()  + "', " +
                    "segundoApellido = '" + empleado.getSegundoApellido() +"', idRol = " +  empleado.getRol().getIdRol() + ", usuario = '" +  empleado.getUsuario() +"', password = '"+ empleado.getPassword() +"', " +
                    "email = '" + empleado.getEmail() +"', superadmin = " + empleado.isSuperAdmin() +", fechaModificacion = '" + empleado.getFechaModificacion() +"', estatus = " + empleado.isStatus() +" " +
                    "WHERE idEmpleado = " + empleado.getIdEmpleado() +";";

        int actualizado = ConnectionFactory.ejecutarSQL(sql);

        return actualizado;
    }

    @Override
    public int eliminar(int idEmpleado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Empleado> consultar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Empleado consultarPorId(int idEmpleado) throws SQLException, RestauranteExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Metodo que permite consultar un empleado desde el login con su formacion
     * y perfil en el sistema
     *
     * @param usuario Parametro capturado por el usuario
     * @param password Parametro capturado por el usuario
     * @param esSuperAdminGeneral vrifica si el usuario es administrador general
     * @return objeto con el empleado logueado
     * @throws SQLException Excepcion en caso de error al ejecutar la Sentencia
     * SQL.
     */
    public Empleado consultarPorUsuarioYPassword(String usuario, String password, boolean esSuperAdminGeneral) throws SQLException {
        Empleado empleado = null;

        String sql = "";

        if (esSuperAdminGeneral) {
            sql = "SELECT e.*, r.nombre AS nombrePerfil "
                    + "FROM restaurante.empleado e, rol r "
                    + "WHERE e.idRol = r.idRol "
                    + "AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') "
                    + "AND e.password = '" + password + "' "
                    + " AND e.idSucursal IS NULL ;";
        } else {
            sql = "SELECT e.*, r.nombre AS nombrePerfil, s.nombre AS nombreSucursal, r.nombre AS nombreRestaurante, res.idRestaurante, res.imagen, res.idMenu "
                    + "FROM empleado e, rol r, sucursal s, restaurante res "
                    + "WHERE e.idRol = r.idRol "
                    + "AND e.idSucursal = s.idSucursal "
                    + "AND s.idRestaurante = res.idRestaurante "
                    + "AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') "
                    + "AND password = '" + password + "';";
        }

        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);

        if (rs != null) {
            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setPrimerApellido(rs.getString("primerApellido"));
                empleado.setSegundoApellido(rs.getString("segundoApellido"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setPassword(rs.getString("password"));
                empleado.setEmail(rs.getString("email"));
                empleado.setStatus(rs.getBoolean("estatus"));
                empleado.setSuperAdmin(rs.getBoolean("superAdmin"));
                empleado.setSuperAdminGeneral(rs.getBoolean("superAdminGeneral"));

                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setNombre(rs.getString("nombrePerfil"));
                empleado.setRol(rol);

                // Agregar funcionalidad para el caso de Administrador de sucursal y empleado
                if (!empleado.isSuperAdminGeneral()) {
                    Sucursal sucursal = new Sucursal();
                    sucursal.setIdSucursal(rs.getInt("idSucursal"));
                    sucursal.setNombre(rs.getString("nombreSucursal"));
                    empleado.setSucursal(sucursal);
                    
                    Restaurante restaurante = new Restaurante();
                    restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
                    restaurante.setNombre(rs.getString("nombreRestaurante"));
                    restaurante.setImagen(rs.getString("imagen"));
                    sucursal.setRestaurante(restaurante);
                    
                    Menu menu = new Menu();
                    menu.setIdMenu(rs.getInt("idMenu"));
                    restaurante.setMenu(menu);
                }

            }
        }

        return empleado;
    }
    
    /**
     * Metodo que permite consultar la lista de empleados del administrador de la sucursal
     * @param idRestauranteUsuarioSesion Identificador del restaurante
     * @return lista de empleados
     * @throws SQLException Excepciion en caso de error al ejecutar la sentencia SQL
     */
    public List<Empleado> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
        List<Empleado> empleadosDB = new ArrayList<>();
        
        String sql = "SELECT e.*, r.nombre as nombrePerfil FROM restaurante res, sucursal s, empleado e, rol r " +
                    "WHERE e.idSucursal = s.idSucursal " +
                    "AND s.idRestaurante = res.idRestaurante " +
                    "AND e.idRol = r.idRol " +
                    "AND s.idRestaurante = " + idRestauranteUsuarioSesion +" ;";
        
        ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
        
        if(rs != null){
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setPrimerApellido(rs.getString("primerApellido"));
                empleado.setSegundoApellido(rs.getString("segundoApellido"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setPassword(rs.getString("password"));
                empleado.setEmail(rs.getString("email"));
                empleado.setStatus(rs.getBoolean("estatus"));
                empleado.setSuperAdmin(rs.getBoolean("superAdmin"));
                empleado.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                empleado.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime() : null);
                
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("idRol"));
                rol.setNombre(rs.getString("nombrePerfil"));
                empleado.setRol(rol);
                
                empleadosDB.add(empleado);
            }
        }
        
        return empleadosDB;
    }

}
