/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Rol;
import com.k1rard.restauranteservice.services.AdminGeneralService;
import com.k1rard.restauranteweb.session.SessionBean;
import com.k1rard.restauranteweb.utils.ControllersUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author k1rard
 */
@Data
@Named
@ViewScoped
public class AdminPerfilesController implements Serializable{
    /**
     * Lista de roles a mostrar en la pantalla
     */
    private List<Rol> perfiles;
    /**
     * Objeto rol a editar o guardar
     */
    private Rol perfil;
    /**
     * Objeto que permite realizar la logica de negocio para la administracion de perfiles
     */
    private AdminGeneralService adminGeneralService = new AdminGeneralService();
    /**
     * Objeto que almacena la informacion del usuario en sesion
     */
    @Inject
    private SessionBean sessionBean;
    /**
     * Metodo que permite inicializar la informacion de la pantalla de adminPerfiles.xhtml
     */
    @PostConstruct
    public void init(){
      this.inicializarComponentes();
      this.consultar();
    }
    
    /**
     * 
     */
    public void inicializarComponentes(){
        this.perfil = new Rol();
    }
    
    /**
     * Metodo que permite cargar la informacion del perfil a editar
     * @param perfil objetp a editar
     */
    public void cargarInformacionModal(Rol perfil){
        this.perfil = perfil;
    }
    
    /**
     * Metodo que permite consultar la lista de roles disponibles,
     */
    public void consultar(){
        try {
            this.perfiles = this.adminGeneralService.consultarRoles();
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al consultar los perfiles disponibles. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminPerfilesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que permite guardar un perfil.
     */
    public void guardar(){
        try {
            int guardado = this.adminGeneralService.guardarRol(this.perfil);
            if(guardado > 0){
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "El perfil " + this.perfil.getNombre() + " fue guardado exitosamente.");
                this.consultar();
            }else{
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El perfil " + perfil.getNombre() + " no pudo ser guardado.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al guardar el perfil. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminPerfilesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que permite actualizar un perfil
     */
    public void actualizar(){
        try {
            int actualizado = this.adminGeneralService.actualizarRol(this.perfil);
            if(actualizado > 0){
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "El perfil " + this.perfil.getNombre() + " fue actualizado exitosamente.");
                this.consultar();
            }else{
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El perfil " + perfil.getNombre() + " no pudo ser actualizado.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al actualizar el perfil. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminPerfilesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
