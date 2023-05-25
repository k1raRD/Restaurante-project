/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteentities.entity;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author KiraRD
 * Clase que representa una entidad de la tabla Tipo de Alimento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TipoAlimento extends CommonEntity{
    /**
     * Identificador del Tipo de Alimento
     */
    private Integer idTipoAlimento;
    /**
     * Identificacion del Tipo de Alimento
     */
    private String descripcion;
    /**
     * Lista de alimentos que contiene el tipo
     */
    private List<Alimento> alimentos;   
}
