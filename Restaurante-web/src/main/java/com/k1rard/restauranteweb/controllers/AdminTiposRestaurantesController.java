package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.TipoRestaurante;
import com.k1rard.restauranteservice.services.AdminGeneralService;
import com.k1rard.restauranteweb.utils.ControllersUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author KiraRD Clase que permite controlar el flujo de la pantalla de
 * administracion de tipos de restaurantes
 */
@Data
@Named
@ViewScoped
public class AdminTiposRestaurantesController implements Serializable {

    /**
     * Lista de tipos de Restaurantes que muestra la informacion en el
     * dataTable.
     */
    private List<TipoRestaurante> tiposRestaurantes;
    /**
     * Objeto para guardar, actualizar y eliminar.
     */
    private TipoRestaurante tipoRestaurante;
    /**
     * Objeto que permite utilizar los servicios de logica de negocio de tipos
     * de restaurantes.
     */
    private AdminGeneralService adminGeneralService = new AdminGeneralService();

    @PostConstruct
    public void init() {

        this.inicializarComponentes();
        this.consultar();
    }

    /**
     * Permite inicializar la informacion de los componentes
     */
    public void inicializarComponentes() {
        this.tipoRestaurante = new TipoRestaurante();
    }

    /**
     * Permite consultar la lista de Tipos de Restaurantes
     */
    public void consultar() {
        try {
            this.tiposRestaurantes = this.adminGeneralService.consultarTiposRestaurantes();
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de restaurantes, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que permite guardar un Tipo de Restaurante
     */
    public void guardar() {
        System.out.println("Ejecutando accion para guardar...");

        try {
            int guardado = this.adminGeneralService.guardarTipoRestaurante(this.tipoRestaurante);

            if (guardado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " guardado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " no se guardo correctamente.");
            }

        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de restaurantes, favor de intentarlo mas tarde.");
            Logger.getLogger(AdminTiposRestaurantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite precargar la informacion del Tipo de Restaurante a
     * editar.
     *
     * @param tipoRestaurante Objeto a editar
     */
    public void cargarInformacionModal(TipoRestaurante tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }

    /**
     * Metodo que permite Actualizar un Tipo de Restaurante
     */
    public void actualizar() {
        System.out.println("Ejecutando accion para Actualizar...");

        try {
            int actualizado = this.adminGeneralService.actualizarTipoRestaurante(this.tipoRestaurante);

            if (actualizado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " guardado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " no se actualizo correctamente.");
            }

        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de restaurantes, favor de intentarlo mas tarde.");
            Logger.getLogger(AdminTiposRestaurantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo que permite eliminar un Tipo de Restaurante.
     */
    public void eliminar() {
        System.out.println("Ejecutando accion para Eliminar...");

        try {
            int eliminado = this.adminGeneralService.eliminarTipoRestaurante(this.tipoRestaurante.getIdTipoRestaurante());

            if (eliminado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " eliminado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Tipo de restaurante " + this.tipoRestaurante.getDescripcion() + " no se elimino correctamente.");
            }

        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de restaurantes, favor de intentarlo mas tarde.");
            Logger.getLogger(AdminTiposRestaurantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
