package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.TipoAlimento;
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
 * @author KiraRD Clase que permite controlar el flujo de la pantalla de
 * administracion de tipos de Alimentos
 */
@Data
@Named
@ViewScoped
public class AdminTiposAlimentosController implements Serializable {

    /**
     * Lista de tipos de Alimentos que muestra la informacion en el
     * dataTable.
     */
    private List<TipoAlimento> tiposAlimentos;
    /**
     * Objeto para guardar o actualizar Tipo de Alimento.
     */
    private TipoAlimento tipoAlimento;
    /**
     * Objeto que almacena la sesion del usuario en cuestion
     */
    @Inject
    private SessionBean sessionBean;
    /**
     * Objeto que permite utilizar los servicios de logica de negocio de tipos
     * de alimentos.
     */
    private AdminSucursalService adminSucursalService = new AdminSucursalService();

    /**
     * Metodo que inicia la pantalla de AdminTiposAlimentos.
     */
    @PostConstruct
    public void init() {

        this.inicializarComponentes();
        this.consultar();
    }

    /**
     * Permite inicializar la informacion de los componentes
     */
    public void inicializarComponentes() {
        this.tipoAlimento = new TipoAlimento();
    }

    /**
     * Permite consultar la lista de Tipos de Alimentos
     */
    public void consultar() {
        try {
            int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
            this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentosPorRestaurante(idRestauranteUsuarioSesion);
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de restaurantes, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que permite guardar un Tipo de Alimento.
     */
    public void guardar() {
        System.out.println("Ejecutando accion para guardar...");

        try {
            int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
            int guardado = this.adminSucursalService.guardarTipoAlimentoRestaurante(this.tipoAlimento, idRestauranteUsuarioSesion);

            if (guardado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de alimento " + this.tipoAlimento.getDescripcion() + " guardado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Tipo de alimento " + this.tipoAlimento.getDescripcion() + " no se guardo correctamente.");
            }

        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de alimentos, favor de intentarlo mas tarde.");
            Logger.getLogger(AdminTiposAlimentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que permite precargar la informacion del Tipo de Restaurante a
     * editar.
     * @param tipoAlimento Objeto a editar
     */
    public void cargarInformacionModal(TipoAlimento tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    /**
     * Metodo que permite Actualizar un Tipo de Restaurante
     */
    public void actualizar() {
        System.out.println("Ejecutando accion para Actualizar...");

        try {
            int actualizado = this.adminSucursalService.actualizarTipoAlimento(this.tipoAlimento);

            if (actualizado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo de Alimento " + this.tipoAlimento.getDescripcion() + " guardado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Tipo de Alimento " + this.tipoAlimento.getDescripcion() + " no se actualizo correctamente.");
            }

        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los tipos de Alimentos, favor de intentarlo mas tarde.");
            Logger.getLogger(AdminTiposAlimentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
