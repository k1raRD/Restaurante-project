package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Menu;
import com.k1rard.restauranteentities.entity.Restaurante;
import com.k1rard.restauranteentities.entity.TipoRestaurante;
import com.k1rard.restauranteservice.services.AdminGeneralService;
import com.k1rard.restauranteweb.utils.ControllersUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Data;

/**
 *
 * @author KiraRD Clase que permite controlar el flujo de la pantalla de
 * administracion de restaurantes
 */
@Data
@Named
@ViewScoped
public class AdminRestaurantesController implements Serializable {

    /**
     * Lista de Restaurantes que muestra la informacion en el dataTable.
     */
    private List<Restaurante> restaurantes;
    /**
     * Objeto para guardar, actualizar y eliminar.
     */
    private Restaurante restaurante;
    /**
     * Archivo seleccionado con la imagen del restaurante
     */
    private Part archivoSeleccionado;
    /**
     * Direccion destino a guardar la imagen
     */
    private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\KiraRD\\Documents\\NetBeansProjects\\Proyecto-Restaurante-Web\\Restaurante-web\\src\\main\\webapp\\resources\\images\\restaurantes\\";
    /**
     * Objeto que permite utilizar los servicios de logica de negocio de tipos
     * de restaurantes.
     */
    private AdminGeneralService adminGeneralService = new AdminGeneralService();

    /**
     * Metodo que inicia la pantalla de AdminRestaurantes
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
        this.restaurante = new Restaurante();
        this.restaurante.setTipoRestaurante(new TipoRestaurante());
        this.restaurante.setMenu(new Menu());
    }

    /**
     * Permite consultar la lista de Restaurantes
     */
    public void consultar() {
        try {
            this.restaurantes = this.adminGeneralService.consultarRestaurantes();
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los restaurantes, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que permite guardar la imagen en un directorio.
     */
    public void guardarImagenDirectorio() {
        if (this.archivoSeleccionado != null) {
            try {
                InputStream inputStream = this.archivoSeleccionado.getInputStream();
                String nombreArchivo = this.archivoSeleccionado.getSubmittedFileName();
                Files.copy(inputStream, new File(PATH_DESTINO_ARCHIVO, nombreArchivo).toPath());
                this.restaurante.setImagen(nombreArchivo);
            } catch (IOException ex) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al copiar la imagen en el directorio, favor de intentarlo mas tarde.");
                ex.printStackTrace();
            }
        }
    }
    /**
     * 
     * Metodo que permite guardar un Restaurante.
     */
    public void guardar() {

        try {
            this.guardarImagenDirectorio();
            int guardado = this.adminGeneralService.guardarRestaurante(this.restaurante);
            if (guardado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", " Restaurante " + this.restaurante.getNombre() + " guardado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", " Restaurante " + this.restaurante.getNombre() + " no guardado.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al procesar la informacion del restaurante, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }
    /**
     * Metodo que permite actualizar un Restaurante.
     */
    public void actualizar() {
        try {
            this.guardarImagenDirectorio();
            int actualizado = this.adminGeneralService.actualizarRestaurante(this.restaurante);
            if (actualizado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", " Restaurante " + this.restaurante.getNombre() + " actualizado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", " Restaurante " + this.restaurante.getNombre() + " no actualizado.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al procesar la informacion del restaurante, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que permite precargar la informacion del Restaurante a editar.
     *
     * @param restaurante Objeto a editar
     */
    public void cargarInformacionModal(Restaurante restaurante) {
        this.restaurante = restaurante;
    }


}
