package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import remarema.api.CreateDeployment;
import remarema.api.CreateSoftwareversion;
import remarema.api.NetworkDetail;
import remarema.api.NodeDetail;
import remarema.domain.Deploy;
import remarema.domain.Network;
import remarema.domain.Node;
import remarema.domain.Softwarepackage;
import remarema.domain.Softwareversion;
import remarema.services.network.IPAddress;

/**
 * Session Bean implementation class DeployServiceBean
 */
@Stateless
@LocalBean
public class DeployServiceBean {

	@PersistenceContext
	public EntityManager em;

	public DeployServiceBean() {
	}

	public DeployServiceBean(EntityManager em) {
		this.em = em;
	}

	public void execute(CreateDeployment command) {
		ArrayList<NetworkDetail> networkDetails = command.getNetworks();
		ArrayList<Network> networks = getNetworkForNetworkDetails(networkDetails);

		Softwareversion software = new Softwareversion();
		software.setSoftwareID(command.getSoftwareversionID());
		
		
		Deploy deploy = new Deploy(software);
		deploy.setNetworks(networks);
		deploy.setDeployDateTime(command.getDeployDateTime());
		deploy.setInstallationDateTime(command.getInstallationDateTime());
		
		em.persist(deploy);
	}
	
	private ArrayList<Network> mapNetworkDetailToNetwork(ArrayList<NetworkDetail> results) {
		ArrayList<Network> networkList = new ArrayList<Network>();

		for (NetworkDetail result : results) {
			Network network = new Network();
			network.setNetworkID(result.getNetworkID());

			networkList.add(network);
		}
		return networkList;
	}
	
	public ArrayList<Network> getNetworkForNetworkDetails(ArrayList<NetworkDetail> results){
		return mapNetworkDetailToNetwork(results);
	}

}
