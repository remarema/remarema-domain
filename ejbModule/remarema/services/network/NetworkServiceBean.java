package remarema.services.network;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import remarema.api.*;
import remarema.domain.Network;

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
	public NetworkServiceBean() {

	}

	public NetworkServiceBean(EntityManager em) {
		this.em = em;
	}

	public void execute(CreateNetwork command) {
		String parentNetworkName = command.getParentNetworkName();

		Network parentNetwork = findParentNetwork(parentNetworkName);

		Network nw = new Network(command.getNetworkName(), parentNetwork);
		em.persist(nw);
	}

	private Network findParentNetwork(String parentNetworkName) {
		if (parentNetworkName == null) {
			return null;
		}
		TypedQuery<Network> query = em.createQuery(
				"select o from Network o where o.networkName = :name",
				Network.class);
		query.setParameter("name", parentNetworkName);
		return query.getSingleResult();
	}
	
	
	public List<NetworkDetail> getNetworkDetailForAllNetworks(){
		List<Network> results = loadNetworks();
		return mapNetworksToNetworkDetail(results);
		
	}

	private List<NetworkDetail> mapNetworksToNetworkDetail(List<Network> results) {
		List<NetworkDetail> networkDetail = new ArrayList<NetworkDetail>();
		
		for(Network result : results){
			NetworkDetail detail = new NetworkDetail();
			
			detail.setNetworkID(result.getNetworkID());
			detail.setNetworkName(result.getNetworkName());
			detail.setNetworkParentID(result.getNetworkID());
			//detail.setNetworkParentName(result.getParent().getNetworkName());
			
			networkDetail.add(detail);
		
		}
		return networkDetail;
	}

	private List<Network> loadNetworks() {
		TypedQuery<Network> query = em.createQuery(
				"SELECT o FROM Network o ORDER BY o.networkID", Network.class);
		
		List<Network> results = query.getResultList();
		return results;
	}
	
	
	public void networkUpdate(NetworkDetail parameterObject) {
		Network nw = em.find(Network.class, parameterObject.getNetworkID());
		em.getTransaction().begin();
		nw.setNetworkName(parameterObject.getNetworkName());
		em.getTransaction().commit();
	}
	

	public void removeNetwork(NetworkDetail parameterObject) {
		Network nw = em.find(Network.class, parameterObject.getNetworkID());
		if (nw != null) {
			em.remove(nw);
		}
	}

}
