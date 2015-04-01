package remarema.services.network;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.NetworkDetail;
import remarema.domain.*;



/**
 * Session Bean implementation class NetworkServiceBean
 */
@Stateless
@LocalBean
public class NetworkServiceBean {

	@PersistenceContext
	protected EntityManager em;
	
	/**
	 * EJB default constructor
	 */
	public NetworkServiceBean(){
		
	}
	
    public NetworkServiceBean(EntityManager em) {
        this.em = em;
    }
    
    
    
    public Network createNetwork(NetworkDetail parameterObject){
    	Network network = em.find(Network.class, parameterObject.networkParentID);
    	Network nw = new Network(parameterObject.networkName, network);
    	em.persist(nw);
    	return nw;	
    }
    
    public void networkUpdate(NetworkDetail parameterObject){
    	Network nw = em.find(Network.class, parameterObject.networkID );
    	em.getTransaction().begin();
    	nw.setNetworkName(parameterObject.networkName);
    	em.getTransaction().commit();
    }
    
    public void removeNetwork(NetworkDetail parameterObject){
		Network nw = findNetwork(new NetworkDetail(parameterObject.networkID));
		if(nw != null){
			em.remove(nw);
		}
	}
    
    public Network findNetwork(NetworkDetail networkDetail) {
		return em.find(Network.class, networkDetail.networkID);
	}
    
    public String findNetworkName(int NetworkID){
		Network nw = em.find(Network.class, NetworkID);
		return nw.getNetworkName();
	}
	
    
    public List<Network> findAllNetworks(){
		TypedQuery<Network> query = em.createQuery(
				"SELECT nw FROM Network nw",Network.class);
		return query.getResultList();
	}
    
    /**
     * Gibt die um 1 erh�hte Anzahl der Netzwerke zur�ck
     * 
     * @return Anzahl der Netzwerke
     */
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
    	}
    	return networksString;
    	
    }
   
    
    

}
