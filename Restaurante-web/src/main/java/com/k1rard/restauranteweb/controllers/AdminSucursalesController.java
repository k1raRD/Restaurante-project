/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Restaurante;
import com.k1rard.restauranteentities.entity.Sucursal;
import com.k1rard.restauranteservice.services.AdminSucursalService;
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
 * Clase controller que permite la comunicacion con la pantalla de administracionSucursales.xhtml
 */
@Data
@Named
@ViewScoped
public class AdminSucursalesController implements Serializable{
    /**
     * Lista de sucursales a mostrar en el datatable de la pantalla
     */
    private List<Sucursal> sucursales;
    /**
     * Objeto a guardar o actualizar de sucursal
     */
    private Sucursal sucursal;
    /**
     * Objeto que contiene la informacion del usuario en sesion.
     */
    @Inject
    private SessionBean sessionBean;
    /**
     * Objeto que permite realizar la logica de negocio para el administrador de sucursal o de restaurante
     */
    private AdminSucursalService adminSucursalService = new AdminSucursalService();
    /**
     * Metodo que permite inicializar la informacion de la pantalla de adminSucursales.xhtml
     */
    @PostConstruct
    public void init(){
        this.inicializarComponentes();
        this.consultar();
    }
    
     /**
     * Permite inicializar la informacion de los componentes
     */
    public void inicializarComponentes() {
        this.sucursal = new Sucursal();
        this.sucursal.setRestaurante(new Restaurante());
    }
    
     /**
     * Metodo que permite precargar la informacion de la sucursal a editar.
     *
     * @param sucursal Objeto a editar
     */
    public void cargarInformacionModal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    /**
     * Metodo que permite consultar las sucursales del usuario en sesion.
     */
    public void consultar(){
        int idRestaurante = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
        
        try {
            this.sucursales = this.adminSucursalService.consultarSucursalesPorRestaurante(idRestaurante);
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para consultar las sucursales. Favor de intentarlo mas tarde");
            Logger.getLogger(AdminSucursalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que permite guardar una sucursal
     */
    public void guardar(){
        Restaurante restaurante = this.sessionBean.getEmpleado().getSucursal().getRestaurante();
        this.sucursal.setRestaurante(restaurante);

        try {            
           int guardado =  this.adminSucursalService.guardarSucursal(sucursal);
           
            if (guardado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "La sucursal " + this.sucursal.getNombre() + " guardada exitosamente.");
                this.consultar();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "ERROR", "La sucursal " + this.sucursal.getNombre() + " no fue guardada");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para guardar la sucursal. Favor de intentarlo mas tarde");
            Logger.getLogger(AdminSucursalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      /**
     * Metodo que permite actualizar una sucursal
     */
    public void actualizar(){
        
        try {            
           int actualizado =  this.adminSucursalService.actualizarSucursal(sucursal);
           
            if (actualizado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "La sucursal " + this.sucursal.getNombre() + " actualizada exitosamente.");
                this.inicializarComponentes();
                this.consultar();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "ERROR", "La sucursal " + this.sucursal.getNombre() + " no fue actualizada");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para actualizar la sucursal. Favor de intentarlo mas tarde");
            Logger.getLogger(AdminSucursalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
