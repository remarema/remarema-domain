package remarema.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.domain.*;



/**
 * Session Bean implementation class NetworkServiceBean
 */
@Stateless
@LocalBean
public class NetworkServiceBean {

	@PersistenceContext
	protected EntityManager em;
	
    public NetworkServiceBean(EntityManager em) {
        this.em = em;
    }
    
    public Network createNetwork(int networkID, String networkName, String networkIP){
    	Network nw = new Network(networkID);
    	nw.setNetworkName(networkName);
    	nw.setNetworkIP(networkIP);
    	em.persist(nw);
    	return nw;	
    }
    
    public void removeNetwork(int networkID){
		Network nw = findNetwork(networkID);
		if(nw != null){
			em.remove(nw);
		}
	}
    
    public Network findNetwork(int networkID) {
		return em.find(Network.class, networkID);
	}
    
    public List<Network> findAllNetworks(){
		TypedQuery<Network> query = em.createQuery(
				"SELECT nw FROM Network nw",Network.class);
		return query.getResultList();
	}
    
    public int findAnzahlNetworks(){
		int a = findAllNetworks().size()+1;    //+1, da wir unten in der Schleife mit 1 beginnen
		return a;
	}
    
    public String[][][] networkArray(){
    	List<Network> networkList = new ArrayList<Network>();
    	networkList = findAllNetworks();
    	int networkAnzahl = findAnzahlNetworks();

    	String[][][] networksString = new String[networkAnzahl][networkAnzahl][networkAnzahl];
    	
    	
    	for(int i = 1; i < networkAnzahl; i++){
    		networksString[i][0][0] = Integer.toString(networkList.get((i-1)).getNetworkID());
        	networksString[i][i][0] = networkList.get((i-1)).getNetworkName();
        	networksString[i][i][i] = networkList.get((i-1)).getNetworkIP();	
    	}
    	return networksString;
    	
    }
   
    
    

}
