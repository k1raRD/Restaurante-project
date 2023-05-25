/**
 * 
 */
package com.k1rard.restauranteentities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KiraRD
 * Clase que representa una entidad de menu de la base de datos
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends CommonEntity{
	/**
	 *  Identificador del menu
	 */
	private Integer idMenu;
	/**
	 *  Clave del menu
	 */
	private String clave;
	/**
	 *  Descripcion del menu
	 */
	private String descripcion;
}
