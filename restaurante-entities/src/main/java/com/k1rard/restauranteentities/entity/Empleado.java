/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteentities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KiraRD
 * Clase que representa un entidad de Empleado.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Empleado extends CommonEntity{
   /**
    * Identificador del empleado
    */
    private Integer idEmpleado;
    /**
     * Nombre del empleado
     */
    private String nombre;
    /**
     * Primer apellido del empleado
     */
    private String primerApellido;
    /**
     * Segundo apellido del empleado
     */
    private String segundoApellido;
    /**
     * Rol del empleado 
     */
    private Rol rol;
    /**
     * Sucursal del empleado
     */
    private Sucursal sucursal;
    /**
     * Usuario del empleado
     */
    private String usuario;
    /**
     * Password del empleado
     */
    private String password;
    /**
     * Email del empleado
     */
    private String email;
    /**
     * Rol super admin
     */
    private boolean superAdmin;
    /**
     * Rol superAdminGeneral
     */
    private boolean superAdminGeneral;    
}
