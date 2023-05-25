package com.k1rard.restauranteservice.services;

import com.k1rard.restauranteentities.entity.*;
import com.k1rard.restaurantesdata.dao.impl.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author KiraRD Clase de servicio que se encarga de realizar la logica de
 * negocio para la administracion de restaurantes para el administrador general.
 */
public class AdminGeneralService {

    /**
     * Objeto que permmite realizar las transacciones para la tabla de Tipos de
     * Restaurantes
     */
    private TipoRestauranteDAOImpl tipoRestauranteDAOImpl = new TipoRestauranteDAOImpl();
    /**
     * Objeto que permite realizar las transacciones para la tabla de
     * Restaurantes
     */
    private RestauranteDAOImpl restauranteDAOImpl = new RestauranteDAOImpl();
    /**
     * Objeto que permite realizar las transacciones para la tabla rol
     */
    private RolDAOImpl rolDAOImpl = new RolDAOImpl();

    //:::::::::::::::: Tipos de Restaurantes ::::::::::::::::::::::::
    public List<TipoRestaurante> consultarTiposRestaurantes() throws SQLException {
        return this.tipoRestauranteDAOImpl.consultar();
    }

    /**
     * Metodo que permite guardar un registro dee Tipo de Restaurante
     *
     * @param tipoRestaurante es el Tipo de Restaurante a guardar
     * @return 1 en caso de guardado, 0 en caso de ERROR
     * @throws SQLException Excepcion lanzada en cado de un ERROR al ejecutar la
     * sentencia SQL en la base de datos
     */
    public int guardarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
        tipoRestaurante.setFechaCreacion(LocalDateTime.now());
        tipoRestaurante.setStatus(true);

        return this.tipoRestauranteDAOImpl.guardar(tipoRestaurante);
    }

    /**
     * Metodo que permite actualizar un registro dee Tipo de Restaurante
     *
     * @param tipoRestaurante es el Tipo de Restaurante a actualizar
     * @return 1 en caso de actualizado, 0 en caso de ERROR
     * @throws SQLException Excepcion lanzada en cado de un ERROR al ejecutar la
     * sentencia SQL en la base de datos
     */
    public int actualizarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
        tipoRestaurante.setFechaModificacion(LocalDateTime.now());

        return this.tipoRestauranteDAOImpl.actualizar(tipoRestaurante);
    }

    /**
     * Metodo que permite eliminar un registro de Tipo de Restaurante
     *
     * @param idTipoRestaurante identificador de Tipo de Resturante a eliminar
     * @return 1 en caso de actualizado, 0 en caso de ERROR
     * @throws SQLException Excepcion lanzada en cado de un ERROR al ejecutar la
     * sentencia SQL en la base de datos
     */
    public int eliminarTipoRestaurante(int idTipoRestaurante) throws SQLException {
        return this.tipoRestauranteDAOImpl.eliminar(idTipoRestaurante);
    }

    // :::::::::::::::::::::::::::::::::::::; Restaurantes ::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Metodo que permite consultar el listado de Restaurantes
     *
     * @return lista de Restaurantes consultados.
     * @throws SQLException Excepcion lanzada en cado de un ERROR al ejecutar la
     * sentencia SQL en la base de datos
     */
    public List<Restaurante> consultarRestaurantes() throws SQLException {
        return this.restauranteDAOImpl.consultar();
    }

    /**
     * Metodo que permite guardar un Restaurante
     *
     * @param restaurante Onjeto a guardar
     * @return 1 en caso de actualizado, 0 en caso de ERROR
     * @throws SQLException Excepcion lanzada en cado de un ERROR al ejecutar la
     * sentencia SQL en la base de datos
     */
    public int guardarRestaurante(Restaurante restaurante) throws SQLException {
        restaurante.setFechaCreacion(LocalDateTime.now());
        restaurante.setStatus(true);
        return this.restauranteDAOImpl.guardar(restaurante);
    }

    /**
     * Metodo que permite actualizar un Restaurante
     *
     * @param restaurante Objeto a acualizar
     * @return 1 en caso de actualizado, 0 en caso de ERROR
     * @throws SQLException Excepcion lanzada en cado de un ERROR al ejecutar la
     * sentencia SQL en la base de datos
     */
    public int actualizarRestaurante(Restaurante restaurante) throws SQLException {
        restaurante.setFechaModificacion(LocalDateTime.now());
        restaurante.setMenu(new Menu());
        return this.restauranteDAOImpl.actualizar(restaurante);
    }

    // :::::::::::::::::::::::::::::::: Roles :::::::::::::::::::::::::::::::
    
    /**
     * Metodo que permite consultar la lista de roles de la aplicacion 
     * @return lista de roles
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Rol> consultarRoles() throws SQLException{
        return this.rolDAOImpl.consultar();
    }
    
    /**
     * Metodo que permite guardar un rol
     * @param rol objeto a guardar
     * @return 1 en caso de ser exitoso o 0 en caso de no serlo
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public int guardarRol(Rol rol) throws SQLException{
        rol.setFechaCreacion(LocalDateTime.now());
        rol.setFechaModificacion(LocalDateTime.now());
        rol.setStatus(true);
        return this.rolDAOImpl.guardar(rol);
    }
    
    /**
     * Metodo que permite actualizar un rol
     * @param rol objeto a actualizar
     * @return 1 en caso de actualizado o 0 en caso de no serlo.
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public int actualizarRol(Rol rol) throws SQLException{
        rol.setFechaModificacion(LocalDateTime.now());
        return this.rolDAOImpl.actualizar(rol);
    }
}
