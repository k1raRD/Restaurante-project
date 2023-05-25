/**
 * 
 */
package com.k1rard.restaurantesdata.enums;

/**
 * @author KiraRD.
 * Enumeracion que contienen los codigos de error del aplicativo.
 */
public enum CodigoEnum {
	/**
	 * CONSTANTE CON EL CODIGO DE ERROR DE SINTAXIS.
	 */
	SINTAXIS_SQL_ERROR_CODE(1064);
	/**
	 * Codigo de error.
	 */
	private int code;
	/**
	 * Constructor privado default de la enumeracion
	 * @param code codigo del generado.
	 */
	CodigoEnum(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
}
