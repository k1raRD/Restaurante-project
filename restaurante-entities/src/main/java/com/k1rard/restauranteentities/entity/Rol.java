package com.k1rard.restauranteentities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author KiraRD
 * Clase que representa la entidad de rol en la aplicacion.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Rol extends CommonEntity{
    /**
     * Identificador del rol.
     */
    private Integer idRol;
    /**
     * Nombre del rol.
     */
    private String nombre;
}
