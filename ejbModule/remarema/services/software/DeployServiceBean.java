package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.CreateDeployment;
import remarema.api.DeployDetail;
import remarema.api.NetworkDetail;
import remarema.api.NodeDetail;
import remarema.domain.Deploy;
import remarema.domain.Network;
import remarema.domain.Node;
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
	
	public void removeDeploy(DeployDetail deployDetail) {
		Deploy deploy = em.find(Deploy.class, deployDetail.getDeployID());
		if(deploy != null){
			em.remove(deploy);
		}
	}

	private ArrayList<Network> mapNetworkDetailToNetwork(
			ArrayList<NetworkDetail> results) {
		ArrayList<Network> networkList = new ArrayList<Network>();

		for (NetworkDetail result : results) {
			Network network = new Network();
			network.setNetworkID(result.getNetworkID());

			networkList.add(network);
		}
		return networkList;
	}

	public ArrayList<Network> getNetworkForNetworkDetails(
			ArrayList<NetworkDetail> results) {
		return mapNetworkDetailToNetwork(results);
	}

	public List<DeployDetail> getDeployDetailForAllDeployments() {
		List<Deploy> results = loadAllDeployments();
		return mapDeployToDeployDetail(results);
	}
	
	private List<DeployDetail> mapDeployToDeployDetail(List<Deploy> results) {
		List<DeployDetail> deployDetail = new ArrayList<DeployDetail>();
		
		for(Deploy result : results){
			DeployDetail detail = new DeployDetail();
			detail.setDeployID(result.getDeployID());
			detail.setDeployDateTime(result.getDeployDateTime());
			detail.setInstallationDateTime(result.getInstallationDateTime());
			
			if(result.hasSoftwareversion()){
				Softwareversion software = result.getSoftwareversion();
				detail.setSoftwareID(software.getSoftwareID());
				detail.setVersionName(software.getVersionName());
			}
			
			List<NetworkDetail> nwDetail = new ArrayList<NetworkDetail>();
			List<Network> allNetworks = result.getNetworks();
			for(Network nw : allNetworks){
				NetworkDetail networkDetail = new NetworkDetail();
				networkDetail.setNetworkID(nw.getNetworkID());
				networkDetail.setNetworkName(nw.getNetworkName());
				nwDetail.add(networkDetail);
				detail.setNetworks(nwDetail);
			}
			
			deployDetail.add(detail);
		}
		
		return deployDetail;
	}

	List<Deploy> loadAllDeployments() {
		TypedQuery<Deploy> query = em.createQuery(
				"SELECT o FROM Deploy o ORDER BY o.deployID", Deploy.class);
		List<Deploy> results = query.getResultList();
		return results;
	}

	public DeployDetail getDeployDetailForDeployID(DeployDetail deployDetail) {
		int deployID = deployDetail.getDeployID();
		return mapDeployToDeployDetail(loadDeploy(deployID));

	}
	
	Deploy loadDeploy(int deployID) {
		TypedQuery<Deploy> query = em.createQuery(
				"SELECT n FROM Deploy n WHERE n.deployID =" + deployID, Deploy.class);

		return query.getSingleResult();
	}
	
	DeployDetail mapDeployToDeployDetail(Deploy deploy) {
		DeployDetail deployDetail = new DeployDetail();
		deployDetail.setDeployID(deploy.getDeployID());
		deployDetail.setDeployDateTime(deploy.getDeployDateTime());
		deployDetail.setInstallationDateTime(deploy.getInstallationDateTime());
		
		if(deploy.hasSoftwareversion()){
			Softwareversion software = deploy.getSoftwareversion();
			int packageID = software.getSoftwarepackage().getSoftwarepackageID();
			String packageName = software.getSoftwarepackage().getSoftwarepackageName();
			deployDetail.setSoftwareID(software.getSoftwareID());
			deployDetail.setVersionName(software.getVersionName());
			deployDetail.setPackageID(packageID);
			deployDetail.setPackageName(packageName);
		}
		
		List<NetworkDetail> nwDetail = new ArrayList<NetworkDetail>();
		List<Network> allNetworks = deploy.getNetworks();
		
		for(Network networks : allNetworks){
			NetworkDetail networkDetail = new NetworkDetail();
			networkDetail.setNetworkID(networks.getNetworkID());
			networkDetail.setNetworkName(networks.getNetworkName());
			nwDetail.add(networkDetail);
			deployDetail.setNetworks(nwDetail);
		}
		
		return deployDetail;
	}

	
}
