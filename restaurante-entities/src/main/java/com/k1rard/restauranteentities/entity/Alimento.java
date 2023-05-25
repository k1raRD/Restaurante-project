/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteentities.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author KiraRD
 * Clase que representa una entidad de la tabla alimento.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Alimento extends CommonEntity{
    /**
     * Identificador del Alimento.
     */
    private Integer idAlimento;
    /**
     * Nombre del Alimento
     */
    private String nombre;
    /**
     * Estado del Alimento.
     */
    private boolean estatus;
    /**
     * Imagen del Alimento.
     */
    private String imagen;
    /**
     * Descripcion del Alimento.
     */
    private String descripcion;
    /**
     * Tipo de Alimento al que pertenece.
     */
    private TipoAlimento tipoAlimento;
    /**
     * Descuento del Alimento.
     */
    private double descuento;
    /**
     * Precio unitario del Alimento
     */
    private double precio;
    /**
     * Restaurante donde se encuentra el Alimento.
     */
    private Restaurante restaurante;
    /**
     * Menu al que se asigna el Alimento.
     */
    private Menu menu;
}
