package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Alimento;
import com.k1rard.restauranteentities.entity.Menu;
import com.k1rard.restauranteservice.services.AdminSucursalService;
import com.k1rard.restauranteweb.session.SessionBean;
import com.k1rard.restauranteweb.utils.ControllersUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author k1rard
 * Clase controller que permite la comunicacion con la pantalla de adminMenu.xhtml
 */
@Data
@Named
@ViewScoped
public class AdminMenuController implements Serializable{
   private static final Logger logger = LogManager.getLogger(AdminMenuController.class);
    /**
     * Lista de alimentos disponibles a realizar con draggable.
     */
    private List<Alimento> alimentosDisponibles;
    /**
     * Lista de alimentos asignados al menu.
     */
    private List<Alimento> alimentosAsignados;
    /**
     * Menu del restaurante del usuario;
     */
    private Menu menu;
    /**
     * Objeto que permite realizar la logica de negocio para las pantallas del administrador de sucursal o restaurante.
     */
    private AdminSucursalService adminSucursalService = new AdminSucursalService();
    /**
     * Objeto que contiene la informaciond el usuario en sesion
     */
    @Inject
    private SessionBean sessionBean;
    /**
     * Metodo que incializa la ventana de administracion de menu.
     */
    @PostConstruct
    public void init(){
        this.menu = null;
        this.consultar();
    }
    
    /**
     * Metodo que permite consultar la informacion de los alimentos disponibles y asignados
     */
    public void consultar(){
        
        this.logger.debug("Consultando los alimentos disponibles y asignados al menu del restaurante.");
        this.logger.info("Consultando los alimentos disponibles y asignados al menu del restaurante.");
        // ::::::::::::::: Alimentos Disponibles ::::::::::::::::
        int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
        
        try {
            this.menu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu();
            
            this.alimentosDisponibles = this.adminSucursalService.consultarAlimentosDisponiblesRestaurante(idRestauranteUsuarioSesion);
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un error al procesar la informacion de alimentos disponibles. Favor de intentar mas tarde");
        }
        
        // :::::::::::::::: Alimentos Asignados :::::::::::::::::::
        
        try {
            this.alimentosAsignados = this.adminSucursalService.consultarAlimentosAsignadosMenu(this.menu.getIdMenu());
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un error al procesar la informacion de alimentos disponibles. Favor de intentar mas tarde");
        }
    }
    
    /**
     * Metodo que permite asignar el elemento al Droppable.
     * @param alimentoEvent evento con el alimento a asignar al menu.
     */
    public void asignarAlimentosMenu(DragDropEvent<Alimento> alimentoEvent){
        Alimento alimentoSeleccionado = alimentoEvent.getData();
        
        this.alimentosDisponibles.remove(alimentoSeleccionado);
        this.alimentosAsignados.add(alimentoSeleccionado);
        
        // ::::::::::::::: Guardar Alimentos asignados al Menu :::::::::::::::::::
        
        try {           
            int asignado = this.adminSucursalService.asignarAlimentoMenu(alimentoSeleccionado.getIdAlimento(), this.menu.getIdMenu());
            
            if(asignado > 0){
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento " + alimentoSeleccionado.getNombre() + " asignado exitosamente.");
                this.consultar();
            }else{
               ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Alimento " + alimentoSeleccionado.getNombre() + " no fue asignado al menu."); 
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion del alimento al menu. Favor de intentarlo mas tarde."); 
        }
        
    }
    
    /**
     * Metodo que permite remover un alimento del menu
     * @param alimento objeto a quitar del menu
     */
    public void quitarAlimentoMenu(Alimento alimento){
        try {
            int removido = this.adminSucursalService.quitarAlimentoMenu(alimento.getIdAlimento());
            
            if(removido > 0){
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento " + alimento.getNombre() + " ha sido removido exitosamente.");
                this.consultar();
            }else{
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Alimento " + alimento.getNombre() + " no fue removido del menu."); 
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al tratar de remover el alimento del menu. Favor de intentarlo mas tarde."); 
        }
    }
    
    
}
