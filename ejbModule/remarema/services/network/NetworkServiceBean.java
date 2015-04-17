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
		List<NetworkDetail> networkDetail = null;
		
		for(Network result : results){
			NetworkDetail detail = new NetworkDetail();
			
			detail.setNetworkID(result.getNetworkID());
			detail.setNetworkName(result.getNetworkName());
			detail.setNetworkParentID(result.getNetworkID());
			
			networkDetail.add(detail);
		
		}
		return networkDetail;
	}

	private List<Network> loadNetworks() {
		TypedQuery<Network> query = em.createQuery(
				"SELECT o FROM Network o", Network.class);
		
		List<Network> results = query.getResultList();
		return results;
	}
	
	

	public void networkUpdate(NetworkDetail parameterObject) {
		Network nw = em.find(Network.class, parameterObject.networkID);
		em.getTransaction().begin();
		nw.setNetworkName(parameterObject.networkName);
		em.getTransaction().commit();
	}

	/*
	public void removeNetwork(NetworkDetail parameterObject) {
		Network nw = findNetwork(new NetworkDetail(parameterObject.networkID));
		if (nw != null) {
			em.remove(nw);
		}
	}
	*/

	public Network findNetwork(NetworkDetail networkDetail) {
		return em.find(Network.class, networkDetail.networkID);
	}

	public String findNetworkName(int NetworkID) {
		Network nw = em.find(Network.class, NetworkID);
		return nw.getNetworkName();
	}

	public List<Network> findAllNetworks() {
		TypedQuery<Network> query = em.createQuery("SELECT nw FROM Network nw",
				Network.class);
		return query.getResultList();
	}

	/**
	 * Gibt die um 1 erh�hte Anzahl der Netzwerke zur�ck
	 * 
	 * @return Anzahl der Netzwerke
	 */
	public int findAnzahlNetworks() {
		int a = findAllNetworks().size() + 1; // +1, da wir unten in der
												// Schleife mit 1 beginnen
		return a;
	}

	public String[][][] networkArray() {
		List<Network> networkList = new ArrayList<Network>();
		networkList = findAllNetworks();
		int networkAnzahl = findAnzahlNetworks();

		String[][][] networksString = new String[networkAnzahl][networkAnzahl][networkAnzahl];

		for (int i = 1; i < networkAnzahl; i++) {
			networksString[i][0][0] = Integer.toString(networkList.get((i - 1))
					.getNetworkID());
			networksString[i][i][0] = networkList.get((i - 1)).getNetworkName();
		}
		return networksString;
	}

}
