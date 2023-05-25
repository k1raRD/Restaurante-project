/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.session;

import com.k1rard.restauranteentities.entity.Empleado;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author KiraRD
 * Clase de JSF que almacena la informacion de sesion del usuario.
 */
@Data
@Named
@SessionScoped
public class SessionBean implements Serializable{
    /**
     * Objeto del Empleado que inicia la Sesion.
     */
    private Empleado empleado;
       
}
