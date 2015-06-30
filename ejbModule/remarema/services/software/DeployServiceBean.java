package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.network.NetworkDetail;
import remarema.api.software.CreateDeployment;
import remarema.api.software.DeployDetail;
import remarema.api.software.UpdateDeploy;
import remarema.domain.Deploy;
import remarema.domain.Network;
import remarema.domain.Softwareversion;

/**
 * Das DeployServiceBean stellt alle notwendigen Methoden für die Verwaltung von
 * Softwareverteilungen bereit. Diese Methoden umfassen das Anlegen, Updaten,
 * und Löschen von Sofwareverteilungen.
 * 
 * @author Rebecca van Langelaan
 */
@Stateless
@LocalBean
public class DeployServiceBean {

	@PersistenceContext
	public EntityManager em;

	/**
	 * EJB default constructor
	 */
	public DeployServiceBean() {
	}

	// Dieser Konstruktor ist nur für das Testen notwendig!
	DeployServiceBean(EntityManager em) {
		this.em = em;
	}

	/**
	 * Die Methode <code>execute()</code> dient zur Speicherung einer neuen
	 * Softwareverteilung in der Datenbank. Dazu wird beim Aufruf der Methode
	 * ein CreateDeployment-Objekt mitübergeben. Dieses beinhaltet die selben
	 * Felder wie die Entity <code>Deploy</code>.
	 * 
	 * Es wird eine Liste von NetworkDetail-Objekten erstellt. Danach wird die
	 * Methode {@link #getNetworkForNetworkDetails(ArrayList)} aufgerufen. Dann
	 * wird ein neues Objekt vom Typ Softwareversion erstellt. Für dieses wird
	 * anschließend die Id gesetzt.
	 * 
	 * Es wird ein neues Deploy-Objekt angelegt und die Softwareversion wird als
	 * Parameter mitübergeben. Des weiteren werden die Datenfelder mit den
	 * set-Methoden gefüllt. Mit <code>em.persist</code> wird der Datensatz in
	 * der Datenbank abgespeichert.
	 * 
	 * @param command
	 */
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

	/**
	 * Die Methode <code>updateDeploy()</code> dient zum Updaten einer
	 * Softwareverteilung in der Datenbank. Dazu wird beim Aufruf der Methode
	 * ein UpdateDeployment-Objekt mitübergeben.
	 * 
	 * Es wird eine Liste von NetworkDetail-Objekten erstellt. Danach wird die
	 * Methode {@link #getNetworkForNetworkDetails(ArrayList)} aufgerufen. Dann
	 * wird ein neues Objekt vom Typ Softwareversion erstellt. Für dieses wird
	 * anschließend die Id gesetzt.
	 * 
	 * Es wird mit <code>em.find()</code> ein Deploy-Objekt anhand der Id
	 * gesucht. Des weiteren werden die Datenfelder mit den set-Methoden
	 * gefüllt. Mit <code>em.flush()</code> werden die Änderungen in der
	 * Datenbank gespeichert.
	 * 
	 * @param command
	 */
	public void updateDeploy(UpdateDeploy command) {
		ArrayList<NetworkDetail> deployNetworks = command.getNetworks();
		ArrayList<Network> networks = getNetworkForNetworkDetails(deployNetworks);

		Softwareversion software = new Softwareversion();
		software.setSoftwareID(command.getSoftwareversionID());

		Deploy deploy = em.find(Deploy.class, command.getDeployID());
		deploy.setDeployID(command.getDeployID());
		deploy.setNetworks(networks);
		deploy.setDeployDateTime(command.getDeployDateTime());
		deploy.setInstallationDateTime(command.getInstallationDateTime());
		deploy.setSoftwareversion(software);
		em.flush();
	}

	/**
	 * Die Methode <code>removeDeploy</code> dient zum Löschen einer
	 * Softwareverteilung aus der Datenbank. Dazu sucht man anhand der Deploy-Id
	 * das die Softwareverteilung. Ist das das Deploy-Objekt nicht null, wird
	 * das Objekt mit <code>em.remove()</code> gelöscht.
	 * 
	 * @param deployDetail
	 */
	public void removeDeploy(DeployDetail deployDetail) {
		Deploy deploy = em.find(Deploy.class, deployDetail.getDeployID());
		if (deploy != null) {
			em.remove(deploy);
		}
	}

	/**
	 * Diese Methode benötigt als Parameter eine ArrayList vom Typ
	 * NetworkDetail. Zu Begin wird eine neue Liste vom Typ Network angelegt.
	 * Anhand einer for-each-Schleife wird die übergebene Liste Objekt für
	 * Objekt durchgegangen. Anhand der Id jedes Objektes wird das dazu passende
	 * Network-Objekt in der Datenbank gesucht und zur neu angelegten Liste
	 * hinzugefügt.
	 * 
	 * @param results
	 * @return networkList - ArrayList mit Network-Objekten
	 */
	private ArrayList<Network> mapNetworkDetailToNetwork(
			ArrayList<NetworkDetail> results) {
		ArrayList<Network> networkList = new ArrayList<Network>();

		for (NetworkDetail result : results) {
			Network network = em.find(Network.class, result.getNetworkID());
			if (network != null) {
				networkList.add(network);
			}
		}
		return networkList;
	}

	/**
	 * Diese Methode ruft lediglich die Methode
	 * {@link #mapNetworkDetailToNetwork(ArrayList)} auf.
	 * 
	 * @param results
	 * @return Dem Benutzer wird eine Liste vom Typ Network zurückgegeben.
	 */
	public ArrayList<Network> getNetworkForNetworkDetails(
			ArrayList<NetworkDetail> results) {
		return mapNetworkDetailToNetwork(results);
	}

	/**
	 * Diese Methode beinhaltet zwei Methodenaufrufe. Zuerst wird
	 * {@link #loadAllDeployments()} aufgerufen, danach die Methode
	 * {@link #mapDeployToDeployDetail(List)}.
	 * 
	 * @return
	 */
	public List<DeployDetail> getDeployDetailForAllDeployments() {
		List<Deploy> results = loadAllDeployments();
		return mapDeployToDeployDetail(results);
	}

	/**
	 * Diese Methode "wandelt" die aus der Datenbank ausgelesenen Deployments in
	 * DeployDetail-Objekte um. Diese werden dann wiederum in eine Liste
	 * gespeichert. Beim jeweiligen Deploy-Objekt werden aber auch die
	 * dazugehörigen Netzwerke in einer Liste gespeichert. Diese müssen in
	 * NetworkDetail-Objekte umgewandelt werden.
	 * 
	 * @param results
	 * @return Liste vom Typ DeployDetail
	 */
	private List<DeployDetail> mapDeployToDeployDetail(List<Deploy> results) {
		ArrayList<DeployDetail> deployDetailList = new ArrayList<DeployDetail>();

		for (Deploy result : results) {
			DeployDetail detail = new DeployDetail();
			detail.setDeployID(result.getDeployID());
			detail.setDeployDateTime(result.getDeployDateTime());
			detail.setInstallationDateTime(result.getInstallationDateTime());

			if (result.hasSoftwareversion()) {
				Softwareversion software = result.getSoftwareversion();
				detail.setSoftwareID(software.getSoftwareID());
				detail.setVersionName(software.getVersionName());
			}

			ArrayList<Network> allNetworks = result.getNetworks();
			ArrayList<NetworkDetail> nwDetail = new ArrayList<NetworkDetail>();

			for (Network nw : allNetworks) {
				NetworkDetail networkDetail = new NetworkDetail();
				networkDetail.setNetworkID(nw.getNetworkID());
				networkDetail.setNetworkName(nw.getNetworkName());
				nwDetail.add(networkDetail);
			}

			detail.setNetworks(nwDetail);
			deployDetailList.add(detail);
		}
		return deployDetailList;

	}

	/**
	 * Die Methode ließt alle Deployments aus der Datenbank aus und ordnet diese
	 * nach der Deploy-Id.
	 * 
	 * @return Zurückgegeben wird die Liste aller Deployments
	 */
	List<Deploy> loadAllDeployments() {
		TypedQuery<Deploy> query = em.createQuery(
				"SELECT o FROM Deploy o ORDER BY o.deployID", Deploy.class);
		List<Deploy> results = query.getResultList();
		return results;
	}

	/**
	 * Diese Methode gibt ein <code>Deploy-Detail</code> zurück. Wird die
	 * Methode aufgerufen, muss ein DeployDetail-Objekt mitübergeben werden.
	 * Dieses Objekt besitzt die Id eines Deployments, zu welchem die
	 * Informationen aus der Datenbank geladen werden sollen.
	 * 
	 * @param deployDetail
	 * @return
	 */
	public DeployDetail getDeployDetailForDeployID(DeployDetail deployDetail) {
		int deployID = deployDetail.getDeployID();
		return mapDeployToDeployDetail(loadDeploy(deployID));

	}

	/**
	 * Diese Methode ladet ein Deployment anhand dessen Id aus der Datenbank.
	 * 
	 * @param deployID
	 * @return
	 */
	Deploy loadDeploy(int deployID) {
		TypedQuery<Deploy> query = em.createQuery(
				"SELECT n FROM Deploy n WHERE n.deployID =" + deployID,
				Deploy.class);

		return query.getSingleResult();
	}

	/**
	 * Diese Methode "wandelt" ein aus der Datenbank ausgelesenes Deployment in
	 * ein DeployDetail-Objekt um. Dieses wird dann an den Benutzer
	 * zurückgegeben.
	 * 
	 * @param deploy
	 * @return
	 */
	DeployDetail mapDeployToDeployDetail(Deploy deploy) {
		DeployDetail deployDetail = new DeployDetail();
		deployDetail.setDeployID(deploy.getDeployID());
		deployDetail.setDeployDateTime(deploy.getDeployDateTime());
		deployDetail.setInstallationDateTime(deploy.getInstallationDateTime());

		if (deploy.hasSoftwareversion()) {
			Softwareversion software = deploy.getSoftwareversion();
			int packageID = software.getSoftwarepackage()
					.getSoftwarepackageID();
			String packageName = software.getSoftwarepackage()
					.getSoftwarepackageName();
			deployDetail.setSoftwareID(software.getSoftwareID());
			deployDetail.setVersionName(software.getVersionName());
			deployDetail.setPackageID(packageID);
			deployDetail.setPackageName(packageName);
		}

		ArrayList<NetworkDetail> nwDetail = new ArrayList<NetworkDetail>();
		ArrayList<Network> allNetworks = deploy.getNetworks();

		for (Network networks : allNetworks) {
			NetworkDetail networkDetail = new NetworkDetail();
			networkDetail.setNetworkID(networks.getNetworkID());
			networkDetail.setNetworkName(networks.getNetworkName());
			nwDetail.add(networkDetail);
			deployDetail.setNetworks(nwDetail);
		}

		return deployDetail;
	}

}
