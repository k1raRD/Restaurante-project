/**
 * 
 */
package com.k1rard.restauranteentities.entity;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KiraRD.
 * Clase que representa una entidad de tipo restaurante en la base de datos.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TipoRestaurante extends CommonEntity{
	/**
	 * 	Identificador del tipo del restaurante
	 */
	private Integer idTipoRestaurante;
	/**
	 * 	Descripcion del tipo del restaurante
	 */
	private String descripcion;

	/**
	 * Restaurantes que pertenecen a un tipo de restaurante
	 */
	private List<Restaurante> restaurante;

        public TipoRestaurante() {
            
            }
}
