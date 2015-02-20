package remarema.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.domain.Network;
import remarema.domain.Node;


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

}
