package com.k1rard.restauranteentities.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author KiraRD
 * Clase que contiene campos que se reutilizan en todos los campos que mapean a las tablas
 */
@Data
@EqualsAndHashCode(callSuper = false)
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
}
