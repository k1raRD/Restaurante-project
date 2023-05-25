package com.k1rard.restaurantedata.entity;

import java.time.LocalDateTime;

/**
 * 
 * @author KiraRD
 * Clase que contiene campos que se reutilizan en todos los campos que mapean a las tablas
 */
public class CommonEntity {
	
	/**
	 *  Fecha de creacion del tipo del restaurante
	 */
	private LocalDateTime fechaCreacion;
	/**
	 *  Fecha de modificacion del tipo del restaurante
	 */
	private LocalDateTime fechaModificacion;
	/**
	 *  Estatus habilitado o deshabilitado del tipo del restaurante
	 */
	private boolean status;
	/**
	 * @return the fechaCreacion
	 */
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the fechaModificacion
	 */
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
