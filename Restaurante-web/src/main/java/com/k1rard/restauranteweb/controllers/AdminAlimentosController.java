package com.k1rard.restauranteweb.controllers;

import com.k1rard.restauranteentities.entity.Alimento;
import com.k1rard.restauranteentities.entity.Menu;
import com.k1rard.restauranteentities.entity.Restaurante;
import com.k1rard.restauranteentities.entity.TipoAlimento;
import com.k1rard.restauranteservice.services.AdminSucursalService;
import com.k1rard.restauranteweb.session.SessionBean;
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
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Data;

/**
 *
 * @author KiraRD Clase que permite controlar el flujo de la pantalla de
 * administracion de alimentos
 */
@Data
@Named
@ViewScoped
public class AdminAlimentosController implements Serializable {

    /**
     * Lista de Alimentos que muestra la informacion en el dataTable.
     */
    private List<Alimento> alimentos;
    /**
     * Objeto para guardar, actualizar y eliminar.
     */
    private Alimento alimento;
    /**
     * Tipos de alimentos que pertenecen al restaurante del usuario.
     */
    private List<TipoAlimento> tiposAlimentos;
    /**
     * Archivo seleccionado con la imagen del alimento
     */
    private Part archivoSeleccionado;
    /**
     * Objeto con la informacion en Sesion del usuario
     */
    //@ManagedProperty("#{sessionBean}")
    @Inject
    private SessionBean sessionBean;
    /**
     * Direccion destino a guardar la imagen
     */
    private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\KiraRD\\Documents\\NetBeansProjects\\Proyecto-Alimento-Web\\Alimento-web\\src\\main\\webapp\\resources\\images\\alimentos\\";
    /**
     * Objeto que permite utilizar los servicios de logica de negocio de tipos
     * de alimentos.
     */
    private AdminSucursalService adminSucursalService = new AdminSucursalService();
    
    /**
     * Metodo que inicializa la pantalla de AdminAlimentos.
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
        this.alimento = new Alimento();
        this.alimento.setTipoAlimento(new TipoAlimento());
        this.alimento.setMenu(new Menu());
        this.alimento.setRestaurante(new Restaurante());
    }

    /**
     * Permite consultar la lista de Alimentos
     */
    public void consultar() {
        try {
            this.alimentos = this.adminSucursalService.consultarAlimentosPorRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al solicitar la informacion de los alimentos, favor de intentarlo mas tarde.");
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
                this.alimento.setImagen(nombreArchivo);
            } catch (IOException ex) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al copiar la imagen en el directorio, favor de intentarlo mas tarde.");
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metodo que permite consultar la lista de tipos de alimentos del usario en sesion.
     */
    public void consultarTiposAlimentosPorRestaurante() {
            try {
                this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentosPorRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "ERROR", "Hubo un problema al solicitar la informacion de los tipos alimentos. Favor de intentarlo mas tarde.");
                    e.printStackTrace();
            }
    }
    
    /**
     * 
     * Metodo que permite guardar un Alimento.
     */
    public void guardar() {

        try {
            this.alimento.setRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante());
            this.guardarImagenDirectorio();
            int guardado = this.adminSucursalService.guardarAlimento(this.alimento);
            if (guardado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", " Alimento " + this.alimento.getNombre() + " guardado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", " Alimento " + this.alimento.getNombre() + " no guardado.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al procesar la informacion del alimento, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }
    
    /**
     * Metodo que permite actualizar un Alimento.
     */
    public void actualizar() {
        try {
            this.alimento.setRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante());
            this.guardarImagenDirectorio();
            int actualizado = this.adminSucursalService.actualizarAlimento(this.alimento);
            if (actualizado > 0) {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", " Alimento " + this.alimento.getNombre() + " actualizado exitosamente.");
                this.consultar();
                this.inicializarComponentes();
            } else {
                ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", " Alimento " + this.alimento.getNombre() + " no actualizado.");
            }
        } catch (SQLException ex) {
            ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "UPS!", "Hubo un problema al procesar la informacion del alimento, favor de intentarlo mas tarde.");
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que permite precargar la informacion del Alimento a editar.
     *
     * @param alimento Objeto a editar
     */
    public void cargarInformacionModal(Alimento alimento) {
        this.alimento = alimento;
    }
}
