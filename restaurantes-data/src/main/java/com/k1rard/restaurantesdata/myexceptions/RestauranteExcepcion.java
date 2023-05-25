/**
 * 
 */
package com.k1rard.restaurantesdata.myexceptions;

import com.k1rard.restaurantesdata.enums.CodigoEnum;

/**
 * @author KiraRD.
 * Excepcion personalizada para los errores ocasionados en restaurante
 */
public class RestauranteExcepcion extends Exception {
	
	/**
	 * Codigo generado por la excepcion.
	 */
	private int errorCode;
	/**
	 * Constructor por Default
	 */
	public RestauranteExcepcion() {
	
	}
	/**
	 * Constructor sobrecargado que muestra el codigo y mensaje de error
	 * @param mensaje mensaje a mostrar al usuario. 
	 * @param codigoEnum codigo de error de la enumeracion.
	 */
	public RestauranteExcepcion(String mensaje, CodigoEnum codigoEnum)
	{
		super(mensaje);
		this.errorCode = codigoEnum.getCode();
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
