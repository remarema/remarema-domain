package remarema.services.network;

import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import remarema.api.*;
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
			
			if(result.hasParentNetwork()){
				Network parent = result.getParent();
				detail.setNetworkParentID(parent.getNetworkID());
				detail.setNetworkParentName(parent.getNetworkName());
			}
			
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
	
	
	public void networkUpdate(UpdateNetwork command) {
		Network nw = em.find(Network.class, command.getNetworkID());
		nw.setNetworkName(command.getNetworkName());
		Network parentNetwork = findParentNetwork(command.getNetworkParentName());
		parentNetwork.setNetworkName(command.getNetworkName());
		nw.setParent(parentNetwork);
		em.flush();
	}
	
	
	public void removeNetwork(NetworkDetail parameterObject) throws ChildNotEmptyException {
		Network nw = em.find(Network.class, parameterObject.getNetworkID());
		
		if (nw != null) {
			if(nw.getChildren().isEmpty()){
				em.remove(nw);
			}
			else{
				throw new ChildNotEmptyException("Dieses Netzwerk beinhaltet Clients!");
			}
		}
		else{
			throw new IllegalArgumentException("Netzwerk ist null");
		}
	}

	public NetworkDetail getNetworkDetailForNetworkID(NetworkDetail networkDetail) {
		int networkID = networkDetail.getNetworkID();
		return mapNetworkToNetworkDetail(loadNetwork(networkID));
	}
	
	NetworkDetail mapNetworkToNetworkDetail(Network network){
		NetworkDetail nwd = new NetworkDetail();
		nwd.setNetworkID(network.getNetworkID());
		nwd.setNetworkName(network.getNetworkName());
		
		Network networkParent = network.getParent();
		if(networkParent != null){
			nwd.setNetworkParentID(networkParent.getNetworkID());
			nwd.setNetworkParentName(networkParent.getNetworkName());
		}
		return nwd;
	}

	Network loadNetwork(int networkID) {
		TypedQuery<Network> query = em.createQuery(
				"SELECT o FROM Network o WHERE o.networkID =" + networkID, Network.class);
		return query.getSingleResult();
	}

}
