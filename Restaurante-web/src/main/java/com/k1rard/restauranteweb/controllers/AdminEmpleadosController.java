package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Empleado;
import com.k1rard.restauranteentities.entity.Rol;
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
 * Clase controller que interactua con la pantalla adminEmpleados.xhtml
 */
@Data
@Named
@ViewScoped
public class AdminEmpleadosController implements Serializable{
    /**
     * Lista de empleados a consultarse en la administracion
     */
    private List<Empleado> empleados;
    /**
     * Objeto a guardar o actualizar
     */
    private Empleado empleado;
    /**
     * Lista de roles disponibles para crear un empleado
     */
    private List<Rol> roles;
    /**
     * Objeto de logica de negocio para el usuario de administrador de sucursal o restaurante.
     */
    private AdminSucursalService adminSucursalService = new AdminSucursalService();
    /**
     * Objeto que contien la informacion en sesion de el usuario
     */
    @Inject
    private SessionBean sessionBean;
    /**
     * Inicializa la informacion de la pantalla de admin Empleados.
     */
    @PostConstruct
    public void init(){
        this.consultar();
        this.consultarRoles();
        this.inicializarComponentes();
    }
    /**
     * Metodo que permite inicializar el empleado a guardar o actualizar
     */
    public void inicializarComponentes(){
        this.empleado = new Empleado();
        this.empleado.setRol(new Rol());
        this.empleado.setSucursal(new Sucursal());
    }
    
    /**
     * Metodo que permite consultar la lista de roles para el combo de la ventana modal
     */
    public void consultarRoles(){
        try {
            this.roles = this.adminSucursalService.consultarRolesSinAdminGeneral();
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para consultar roles. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que permite consultar los empleados de la sucursal.
     */
    private void consultar(){
        
        int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
        
        try {
           this.empleados = this.adminSucursalService.consultarEmpleadosPorRestaurante(idRestauranteUsuarioSesion);
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para consultar empleados. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo que permite guardar un objeto de empleado.
     */
    public void guardar(){
        Sucursal sucursalSesion = this.sessionBean.getEmpleado().getSucursal();
        this.empleado.setSucursal(sucursalSesion);
        
        try {
            int guardado = this.adminSucursalService.guardarEmpleado(this.empleado);
            if(guardado > 0){
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Empleado " + this.empleado.getNombre() + " fue guardado exitosamente.");
                this.consultar();
            }else{
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Empleado " + this.empleado.getNombre() + " NO GUARDADO.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para guardar un empleado. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que permite actualizar un empleado.
     */
    public void actualizar(){
        try {
            int actualizado = this.adminSucursalService.actualizarEmpleado(this.empleado);
            if(actualizado > 0){
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Empleado " + this.empleado.getNombre() + " fue actualizado exitosamente.");
                this.consultar();
            }else{
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Empleado " + this.empleado.getNombre() + " NO actualizado .");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS", "Hubo un problema al procesar la informacion para actualizar un empleado. Favor de intentarlo mas tarde.");
            Logger.getLogger(AdminEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que precarga la informaciond el empleado seleccionado a actualizar
     * @param empleado seleccionado a actualizar
     */
    public void cargarInformacionModal(Empleado empleado){
        this.empleado = empleado;
    }    
    
    
}
