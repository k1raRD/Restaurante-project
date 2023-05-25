/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.converters;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author KiraRD
 * Converter de fechas de tipo LocalDateTime
 */
@Named
@ApplicationScoped
public class LocalDateTimeConverter implements Converter, Serializable{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return LocalDateTime.parse(value);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        LocalDateTime localDateTime = (LocalDateTime) value;
        
        Date fecha = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fechaConverter = sdf.format(fecha);
        return fechaConverter;
    }
    
}
