package remarema.services.settings;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SettingsServiceBean
 */
@Stateless
@LocalBean
public class SettingsServiceBean {

    private int lifetime;

	/**
     * EJB default constructor. 
     */
    public SettingsServiceBean() {
        
    }
    
    public void updateLifetime(int lifetime){
    	this.lifetime = lifetime;
    }

}
