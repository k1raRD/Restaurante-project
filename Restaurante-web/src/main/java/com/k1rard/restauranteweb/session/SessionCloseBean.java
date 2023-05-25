/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.session;

import com.k1rard.restauranteweb.utils.ControllersUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author KiraRD
 * Clase que permite cerrar la sesion del usuario.
 */
@Named
@ViewScoped
public class SessionCloseBean implements Serializable{
    /**
     * Metodo que permite cerrar la sesion del usuario.
     */
    @Inject
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        try {
            ControllersUtil.redireccionar("/login.xhtml");
        } catch (IOException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al redirigirte a la pantalla de login, favor de volver a la pantalla del inicio.");
        }
    }
}
