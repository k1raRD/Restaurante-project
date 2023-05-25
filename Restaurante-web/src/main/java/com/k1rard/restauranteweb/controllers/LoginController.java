/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Empleado;
import com.k1rard.restauranteservice.services.LoginService;
import com.k1rard.restauranteweb.session.SessionBean;
import com.k1rard.restauranteweb.utils.ControllersUtil;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author KiraRD Clase bean de JSF que se comunica con la view login.xhtml
 * Nota: Siempre agregar @Named a una clase para realizar este proceso
 */
@Data
@Named
@ViewScoped
public class LoginController implements Serializable {

    /**
     * Usuario capturado por el usuario final.
     */
    private String username;
    /**
     * Password capturada por el usuario final
     */
    private String password;
    /**
     * Indicador para saber si el usuario a ingresar es un administrador
     * general.
     */
    private boolean esSuperAdminGeneral;
    /**
     * Objeto que mantiene la informacion en sesion del aplicativo.
     */
//    @ManagedProperty("#(sessionBean)")
    @Inject
    private SessionBean sessionBean;

    /**
     * Objeto para obtener la logica de negocio del login.
     */
    private LoginService loginService = new LoginService();

    /**
     * Metodo que permite iniciar sesion en la aplicacion.
     */
    public void entrar() {
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un error al iniciar sesion.");
        }

        try {
            Empleado empleadoConsultado = this.loginService.consultarPorUsuarioYPassword(this.username, this.password, this.esSuperAdminGeneral);

            if (empleadoConsultado != null) {
                if (empleadoConsultado.isStatus()) {
                    
                    
                    if (empleadoConsultado.isSuperAdminGeneral()) {
                        ControllersUtil.redireccionar("/pages/admin/adminRestaurantes.xhtml");
                    } else if (empleadoConsultado.isSuperAdmin()) {
                        ControllersUtil.redireccionar("/pages/admin/adminMenu.xhtml");
                    }else{
                        ControllersUtil.redireccionar("/pages/empleado/menuEmpleado.xhtml");
                    }
                    
                    this.sessionBean.setEmpleado(empleadoConsultado);

                } else {
                    ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El usuario esta deshabilitado del sistema.");
                }
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El usuario y/o password son incorrectos");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un problema al procesar su solicitud, favor de intentarlo mas tarde.");
        } catch (IOException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un problema al acceder a tu pantalla de inicio, favor de intentarlo mas tarde.");
        }
    }

    
}
