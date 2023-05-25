/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k1rard.restauranteweb.filters;

import com.k1rard.restauranteentities.entity.Empleado;
import com.k1rard.restauranteweb.session.SessionBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author k1rard
 * Clase filtro que permite verificar la sesion del usuario y en caso de no estar en sesion se le redirige a la pantalla de login.
 */
@WebFilter
public class LoginFilter implements Filter{

    @Override
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        chain.doFilter(req, res);
        
//        if(session == null || session.getAttribute("sessionBean") == null){
//            System.out.println("La sesion es nula");
//            response.sendRedirect(request.getContextPath() + "/login.xhtml");
//        }else{
//            if(session.getAttribute("sessionBean") != null){
//                SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
//                Empleado empleado = sessionBean.getEmpleado();
//                
//                if(empleado == null){
//                    response.sendRedirect(request.getContextPath() + "/login.xhtml");
//                }
//            }
//            
//            chain.doFilter(req, res);
//        }               
    }
    
}
