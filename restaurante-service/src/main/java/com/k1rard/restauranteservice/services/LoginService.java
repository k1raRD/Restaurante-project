/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteservice.services;

import com.k1rard.restauranteentities.entity.Empleado;
import com.k1rard.restaurantesdata.dao.impl.EmpleadoDAOImpl;
import java.sql.SQLException;

/**
 *
 * @author KiraRD Clase que realiza la logica de negocio para la funcionalidad
 * de la pantalla de login
 */
public class LoginService {

    /**
     * Objeto para obtener los resultados de las transacciones a la base de
     * datos en la tabla de empleados
     */
    EmpleadoDAOImpl empleadoDaoImpl = new EmpleadoDAOImpl();

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
        return this.empleadoDaoImpl.consultarPorUsuarioYPassword(usuario, password, esSuperAdminGeneral);
    }
}
