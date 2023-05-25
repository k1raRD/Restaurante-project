package com.k1rard.restauranteservice.services;

import com.k1rard.restauranteentities.entity.Alimento;
import com.k1rard.restauranteentities.entity.TipoAlimento;
import com.k1rard.restaurantesdata.dao.impl.AlimentoDAOImpl;
import com.k1rard.restaurantesdata.dao.impl.TipoAlimentoDAOImpl;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author k1rard
 * Clase que realiza la logica de negocio para la pantalla del empleado
 */
public class EmpleadoService {
    /**
     * Objeto que implementa las transacciones a la tabla tipoAlimento.
     */
    private TipoAlimentoDAOImpl tipoAlimentoDAOImpl = new TipoAlimentoDAOImpl();
    /**
     * Objeto que implementa las transacciones a la tabla alimento.
     */
    private AlimentoDAOImpl alimentoDAOImpl = new AlimentoDAOImpl();
    
     /**
     * Metodo que permite consultar alimentos del menu del restaurante.
     * @param idMenu Identificador del menu
     * @return una lista de tipos de alimentos
     * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL.
     */
    public List<TipoAlimento> consultarAlimentoPorMenu(int idMenu) throws SQLException{
        List<TipoAlimento> tiposAlimentos = this.tipoAlimentoDAOImpl.consultarTiposAlimentosPorMenu(idMenu);
        
        for (TipoAlimento tipoAlimento : tiposAlimentos) {
            List<Alimento> alimentos = this.alimentoDAOImpl.consultarAlimentosPorTipoAndMenu(tipoAlimento.getIdTipoAlimento(), idMenu);
            tipoAlimento.setAlimentos(alimentos);
        }
        
        return tiposAlimentos;
    }
}
