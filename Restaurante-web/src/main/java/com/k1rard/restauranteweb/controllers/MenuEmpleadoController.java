package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.TipoAlimento;
import com.k1rard.restauranteservice.services.EmpleadoService;
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
 * Clase controller que permite interactuar con la pantalla de menuEmpleado.xhtml
 */
@Data
@Named
@ViewScoped
public class MenuEmpleadoController implements Serializable{
    /**
     * Lista de tipos de alimentos a mostrar en el menu con su respectivos alimentos
     */
    private List<TipoAlimento> tiposAlimentos;
    /**
     * Objeto que realiza la logica de negocio para los empleados
     */
    private EmpleadoService empleadoService = new EmpleadoService();
    /**
     * Objeto que contiene la informacion en sesion del usuario
     */
    @Inject
    private SessionBean sessionBean;
    /**
     * Metodo que permite inicializar la pantalla del menu del empleado.
     */
    @PostConstruct
    public void init(){
        consultar();
    }
    
    /**
     * Metodo que permite consultar los alimentos del menu
     */
    public void consultar(){
        int idMenu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu().getIdMenu();
        
        try {
            this.tiposAlimentos = this.empleadoService.consultarAlimentoPorMenu(idMenu);
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al mostrar los tipos de alimentos del menu de restaurante.");
            Logger.getLogger(MenuEmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
