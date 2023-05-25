package beans.configuracion;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * 
 * @author k1rard
 * Clase de configuracion JSF para la version 2.3.
 */
@FacesConfig(
        //Activa CDI build-in beans
        version = JSF_2_3
)
@ApplicationScoped
public class ConfigurationJSF {
    
}
