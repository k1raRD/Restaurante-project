/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteservice.services;

import com.k1rard.restauranteentities.entity.*;
import com.k1rard.restaurantesdata.dao.impl.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author KiraRD
 * Clase que realiza la logica de negocio para la pantalla de administrador de Sucursal
 */
public class AdminSucursalService {
    /**
     * Objeto que permite realizar las transacciones a la base de datos em la tabla Tipo de Alimento
     */
    private TipoAlimentoDAOImpl tipoAlimentoDAOImpl = new TipoAlimentoDAOImpl();
    /**
     * Objeto que permite realizar las transacciones a la base de datos de la tabla de alimentos.
     */
    private AlimentoDAOImpl alimentoDAOImpl = new AlimentoDAOImpl();
    /**
     * Objeto que permite realizar las transacciones a la base de datos de la tabla empleados.
     */
    private EmpleadoDAOImpl empleadoDAOImpl = new EmpleadoDAOImpl();
    /**
     * Objeto que permite realizar las transacciones a la base de datos de la tabla rol.
     */
    private RolDAOImpl rolDAOImpl = new RolDAOImpl();
    /**
     * Objeto que permite realizar las transacciones a la base de datos de la tabla sucursal.
     */
    private SucursalDAOImpl sucursalDAOImpl = new SucursalDAOImpl();
    /**
     * Metodo que permite consultar la Lista de Tipo de Alimentos.
     * @return Regresa la Lista de Tipos de Alimentos.
     * @throws SQLException Regresa la Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<TipoAlimento> consultarTiposAlimentos() throws SQLException{
        return this.tipoAlimentoDAOImpl.consultar();
    }
    /**
     * Metodo que permite guardar un Tipo de Alimento
     * @param tipoAlimento objeto a guardar
     * @return 1 en caso de guardado, 0 en caso de no guardado
     * @throws SQLException Regresa la Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public int guardarTipoAlimento(TipoAlimento tipoAlimento) throws SQLException{
        tipoAlimento.setFechaCreacion(LocalDateTime.now());
        tipoAlimento.setStatus(true);
        
        return this.tipoAlimentoDAOImpl.guardar(tipoAlimento);
    }
    
    /**
     * Metodo que permite actualizar un registro de tipo de alimento perteneciente al restaurante del usuario en sesion.
     * @param tipoAlimento tipo de alimento a guardar
     * @param idRestauranteUsuarioSesion identificador del restaurante del usuario en sesion
     * @return regresa un 1 en caso de actualizado o 0 en caso de error
     * @throws SQLException Excepcion en caso de error de la sentencia SQL.
     */
    public int guardarTipoAlimentoRestaurante(TipoAlimento tipoAlimento, int idRestauranteUsuarioSesion) throws SQLException {
            tipoAlimento.setFechaCreacion(LocalDateTime.now());
            tipoAlimento.setFechaModificacion(LocalDateTime.now());
            tipoAlimento.setStatus(true);

            return this.tipoAlimentoDAOImpl.guardar(tipoAlimento, idRestauranteUsuarioSesion);
    }
    
    /**
     * Metodo que permite actualizar un Tipo de Alimento
     * @param tipoAlimento objeto a actualizar
     * @return 1 en caso de actualizado, 0 en caso de no actualizado
     * @throws SQLException Regresa la Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public int actualizarTipoAlimento(TipoAlimento tipoAlimento) throws SQLException{
        tipoAlimento.setFechaModificacion(LocalDateTime.now());
        
        return this.tipoAlimentoDAOImpl.actualizar(tipoAlimento);
    }
    
    /**
     * Metodo que permite consultar los tipos de alimentos que corresponden al restaurante del usuario en sesion
     * @param idRestauranteUsuarioSesion Identificador del restaurante
     * @return lista de alimentos 
     * @throws SQLException Excepcion en caso de error al ejecutar la 
     */
    public List<TipoAlimento> consultarTiposAlimentosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
            return this.tipoAlimentoDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
    }
    
    
    //::::::::::::::::::::::::::::::::::::::::: ALIMENTOS ::::::::::::::::::::::::::::::::::::::::::::::::::::
    /**
     * Metodo que permite consultar la Lista de Alimentos de un Restaurante
     * @param idRestauranteUsuarioSesion identificador del Restaurante
     * @return Lista de Alimentos.
     * @throws SQLException Regresa la Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<Alimento> consultarAlimentosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
        return this.alimentoDAOImpl.consultarPorIdRestaurante(idRestauranteUsuarioSesion);
    }
    
    	/**
	 * Metodo que permite guardar un registro de alimento.
	 * @param alimento objeto a guardar
	 * @return 1 en caso de guardado o 0 en caso de no guardado
	 * @throws SQLException Excepcion en caso de error de la sentencia SQL.
	 */
	public int guardarAlimento(Alimento alimento) throws SQLException {
		alimento.setFechaCreacion(LocalDateTime.now());
		alimento.setFechaModificacion(LocalDateTime.now());
		alimento.setEstatus(true);
		
		return this.alimentoDAOImpl.guardar(alimento);
	}
        
        	/**
	 * Metodo que permite actualizar registros de alimentos
	 * @param alimento Objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso o 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	public int actualizarAlimento(Alimento alimento) throws SQLException {
		alimento.setFechaModificacion(LocalDateTime.now());
		
		return this.alimentoDAOImpl.actualizar(alimento);
	}
        
         /**
         * Metodo que permite consultar la lista de alimentos disponibles del restaurante no asignado al menu
         * @param idRestauranteUsuarioSesion Identificador del restaurante
         * @return Lista de restaurantes diponibles
         * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
         */
        public List<Alimento> consultarAlimentosDisponiblesRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
            return this.alimentoDAOImpl.consultarAlimentosDisponibles(idRestauranteUsuarioSesion);
        }
        
        /**
         * Metodo que permite consultar la lista de alimentos asignados al menu del restaurante;
         * @param idMenu Identificador del menu;
         * @return Lista de alimentos asignados al menu;
         * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
         */
        public List<Alimento> consultarAlimentosAsignadosMenu(Integer idMenu) throws SQLException{
            return this.alimentoDAOImpl.consultarAlimentosAsignados(idMenu);
        }
        
         /**
         * Metodo que permite asignar un alimento al menu del restaurante
         * @param alimentoSeleccionado alimento seleccionado por el usuario
         * @param idAlimentoSeleccionado Identificador del alimento a asignar al menu
         * @param idMenu Identificador del menu
         * @return Regresa 1 en caso de asignado o 0 en caso de no ser asignado
         * @throws SQLException Excepcion en caso de error al ejecutar el SQL
         */
        public int asignarAlimentoMenu(int alimentoSeleccionado, int idMenu) throws SQLException{
            return this.alimentoDAOImpl.asignarAlimentoMenu(alimentoSeleccionado, idMenu);
        }
        
         /**
         * Metodo que permite quitar un alimento del menu del restaurante.
         * @param idAlimentoSeleccionado Identificador del alimento seleccionado
         * @return 1 en caso de ser removido o 0 en cado de no serlo
         * @throws SQLException Excepcion en caso de no ejecutar la sentencia SQL
         */
        public int quitarAlimentoMenu(int idAlimentoSeleccionado) throws SQLException{
            return this.alimentoDAOImpl.quitarAlimentoMenu(idAlimentoSeleccionado);
        }
        
        // :::::::::::::::::::::::::::: Empleados ::::::::::::::::::::::::
        
         /**
         * Metodo que permite consultar la lista de empleados del administrador de la sucursal
         * @param idRestauranteUsuarioSesion Identificador del restaurante
         * @return lista de empleados
         * @throws SQLException Excepciion en caso de error al ejecutar la sentencia SQL
         */
        public List<Empleado> consultarEmpleadosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
            return this.empleadoDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
        }
        
        /**
         * Metodo que permite guardar un empleado
         * @param empleado objeto a guardar
         * @return 1 en caso de ser removido o 0 en cado de no serlo
         * @throws SQLException Excepciion en caso de error al ejecutar la sentencia SQL
         */
        public int guardarEmpleado(Empleado empleado) throws SQLException{
            // Si el rol a asignar al empleado es adminSucursal
            if(empleado.getRol().getIdRol() == 6){
                empleado.setSuperAdmin(true);
            }
            empleado.setFechaCreacion(LocalDateTime.now());
            empleado.setFechaModificacion(LocalDateTime.now());
            empleado.setStatus(true);
            return this.empleadoDAOImpl.guardar(empleado);
        }
        
         /**
         * Metodo que permite actualizar un empleado
         * @param empleado objeto a actualizar
         * @return 1 en caso de ser actualizado o 0 en cado de no serlo
         * @throws SQLException Excepciion en caso de error al ejecutar la sentencia SQL
         */
        public int actualizarEmpleado(Empleado empleado) throws SQLException{
            // Si el rol a asignar al empleado no es adminSucursal
            if(empleado.getRol().getIdRol() != 6){
                empleado.setSuperAdmin(false);
            }
            
            empleado.setFechaModificacion(LocalDateTime.now());
            
            return this.empleadoDAOImpl.actualizar(empleado);
        }
        
        // ::::::::::::::::::: ROLES :::::::::::::::::::::::::::::;
        
        /**
         * Metodo que permite consultar los roles para el administrador de sucursal que desee guardar un nuevo empleado
         * @return la lista de roles disponibles
         * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
         */
        public List<Rol> consultarRolesSinAdminGeneral() throws SQLException{
            return this.rolDAOImpl.consultarRolesSinAdminGeneral();
        }
        
        // :::::::::::::::::::::::: Sucursales :::::::::::::::::::::::::;;
        
         /**
         * Metodo que permite consultar la lista de sucursales de un restaurante.
         * @param idRestauranteUsuarioSesion Identificador del restaurante.
         * @return devuelve una lista de sucursales.
         * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
         */
        public List<Sucursal> consultarSucursalesPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
           return this.sucursalDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
        }
        
        /**
         * Metodo que permite guardar una sucursal
         * @param sucursal objeto a guardar
         * @return 1 en caso de ser guardado o 0 en cado de no serlo
         * @throws SQLException Excepciion en caso de error al ejecutar la sentencia SQL
         */
        public int guardarSucursal(Sucursal sucursal) throws SQLException{
            sucursal.setFechaCreacion(LocalDateTime.now());
            sucursal.setFechaModificacion(LocalDateTime.now());
            sucursal.setStatus(true);
            return this.sucursalDAOImpl.guardar(sucursal);
        }
        
         /**
	 * Metodo que permite actualizar registros de Sucursal
	 * @param sucursal objeto a actualizar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL.
	 */
        public int actualizarSucursal(Sucursal sucursal) throws SQLException{
            sucursal.setFechaModificacion(LocalDateTime.now());
            return this.sucursalDAOImpl.actualizar(sucursal);
        }

}
