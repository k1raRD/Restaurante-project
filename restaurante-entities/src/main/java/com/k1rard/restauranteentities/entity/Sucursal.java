package com.k1rard.restauranteentities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author KiraRD
 * Clase que representa una entidad de la tabla de sucursal.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Sucursal extends CommonEntity{
    /**
     * Identificador de la sucursal
     */
    private Integer idSucursal;
    /**
     * Nombre de la sucursal
     */
    private String nombre;
    /**
     * Restaurante del cual pertenece
     */
    private Restaurante restaurante;
}
