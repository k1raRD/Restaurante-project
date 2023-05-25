/**
 * 
 */
package com.k1rard.restaurantedata.entity;

import java.util.List;

/**
 * @author KiraRD.
 * Clase que representa una entidad de tipo restaurante en la base de datos.
 */
public class TipoRestaurante extends CommonEntity{
	/**
	 * 	Identificador del tipo del restaurante
	 */
	private int idTipoRestaurante;
	/**
	 * 	Descripcion del tipo del restaurante
	 */
	private String descripcion;

	/**
	 * Restaurantes que pertenecen a un tipo de restaurante
	 */
	private List<Restaurante> restaurante;

	/**
	 * @return the idTipoRestaurante
	 */
	public int getIdTipoRestaurante() {
		return idTipoRestaurante;
	}

	/**
	 * @param idTipoRestaurante the idTipoRestaurante to set
	 */
	public void setIdTipoRestaurante(int idTipoRestaurante) {
		this.idTipoRestaurante = idTipoRestaurante;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the restaurante
	 */
	public List<Restaurante> getRestaurante() {
		return restaurante;
	}

	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(List<Restaurante> restaurante) {
		this.restaurante = restaurante;
	}
}
