package remarema.services.network;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.network.CreateNetwork;
import remarema.api.network.NetworkDetail;
import remarema.api.network.NodeDetail;
import remarema.api.network.UpdateNetwork;
import remarema.domain.Network;
import remarema.domain.Node;

/**
 * Das NetworkServiceBean stellt alle notwendigen Methoden für die Verwaltung
 * von Netzwerken bereit. Diese Methoden umfassen das Anlegen, Updaten, und
 * Löschen von Netzwerken. Diese Klasse beinhaltet weiters Methoden wie z.B.:
 * <code>getNetworkDetailForAllNetworks()</code> um alle Netzwerke auf der
 * Weboberfläche darstellen zu können. Dafür werden aber auch die Methoden
 * <code>loadNetworks()</code> und <code>mapNetworksToNetworkDetail()</code>
 * benötigt.
 * 
 * @author Rebecca van Langelaan
 * 
 */
@Stateless
@LocalBean
public class NetworkServiceBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * EJB default constructor
	 */
	public NetworkServiceBean() {

	}

	// Package-Protected; nur für das Testen gedacht.
	NetworkServiceBean(EntityManager em) {
		this.em = em;
	}

	/**
	 * Die Methode <code>execute()</code> dient zur Speicherung eines neuen
	 * Netzwerkes in der Datenbank. Dazu wird beim Aufruf der Methode ein
	 * CreateNetwork-Objekt mitübergeben. Dieses beinhaltet die selben Felder
	 * wie die Entity <code>Network</code>.
	 * 
	 * Mit Hilfe der Methode <code>findParentNetwork()</code> wird überprüft, ob
	 * das angebene Network-Parent überhaupt existiert. Danach wird mit
	 * <code>Network nw = new Network()</code> ein neues Netzwerk angelegt. Mit
	 * <code>em.persist(nw)</code> wird der Datensatz in der Datenbank angelegt.
	 * 
	 * @param command
	 */
	public void execute(CreateNetwork command) {
		String parentNetworkName = command.getParentNetworkName();

		Network parentNetwork = findParentNetwork(parentNetworkName);

		Network nw = new Network(command.getNetworkName(), parentNetwork);
		em.persist(nw);
	}

	/**
	 * Diese Methode prüft, ob das angebene Netzwerk in der Datenbank existiert.
	 * Wenn ja, wird der Datensatz ausgegeben.
	 * 
	 * @param parentNetworkName
	 * @return Den passenden Datensatz aus der Datenbank Tabelle
	 *         <code>Network</code>
	 */
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

	/**
	 * Wird diese Methode aufgerufen, wird eine Liste vom Typ
	 * <code>NetworkDetail</code> zurückgegeben. Diese Methode wird auf der
	 * Weboberfläche im Menüpunkt "Netzwerkverwaltung" benötigt.
	 * 
	 * @return Liste, der in der Datenbank gespeicherten Netzwerke - aber vom
	 *         Typ <code>Network-Detail</code>
	 */
	public List<NetworkDetail> getNetworkDetailForAllNetworks() {
		List<Network> results = loadNetworks();
		return mapNetworksToNetworkDetail(results);

	}

	/**
	 * Diese Methode "wandelt" die aus der Datenbank ausgelesenen Netzwerke in
	 * Network-Detail-Objekte um. Diese werden dann wiederum in eine Liste
	 * gespeichert.
	 * 
	 * @param results
	 *            Als Parameter wird eine Liste vom Typ <code>Network</code>
	 *            übergeben
	 * @return Zurückgegeben wird die neue Liste vom Typ
	 *         <code>NetworkDetail</code>
	 */
	private List<NetworkDetail> mapNetworksToNetworkDetail(List<Network> results) {
		List<NetworkDetail> networkDetail = new ArrayList<NetworkDetail>();

		for (Network result : results) {
			NetworkDetail detail = new NetworkDetail();

			detail.setNetworkID(result.getNetworkID());
			detail.setNetworkName(result.getNetworkName());

			if (result.hasParentNetwork()) {
				Network parent = result.getParent();
				detail.setNetworkParentID(parent.getNetworkID());
				detail.setNetworkParentName(parent.getNetworkName());
			}
			networkDetail.add(detail);

		}
		return networkDetail;
	}

	/**
	 * Die Methode ließt alle Netzwerke aus der Datenbank aus und ordnet diese
	 * nach der Netzwerk-Id.
	 * 
	 * @return Zurückgegeben wird die Liste aller Netzwerke
	 */
	private List<Network> loadNetworks() {
		TypedQuery<Network> query = em.createQuery(
				"SELECT o FROM Network o ORDER BY o.networkID", Network.class);

		List<Network> results = query.getResultList();
		return results;
	}

	/**
	 * Die Methode <code>networkUpdate()</code> wird dazu benötigt, ein
	 * vorhandenes Netzwerk zu bearbeiten. Zuerst wird geprüft, ob das Netzwerk
	 * überhaupt existiert - dazu ließt man vom übergebenen
	 * <code>UpdateNetwork</code>-Objekt die Id des Netzwerkes aus. Wurde das Netzwerk
	 * gefunden, wird der Name neu gesetzt. Außerdem wird überprüft, ob das Parent-Network existiert 
	 * und wenn ja, wird auch dieses zum Netzwerk hinzugefügt. 
	 * Upgedated wird dann mit dem Befehl <code>flush()</code>.
	 * 
	 * @param command
	 */
	public void networkUpdate(UpdateNetwork command) {
		Network nw = em.find(Network.class, command.getNetworkID());
		nw.setNetworkName(command.getNetworkName());
		Network parentNetwork = findParentNetwork(command
				.getNetworkParentName());
		parentNetwork.setNetworkName(command.getNetworkParentName());
		nw.setParent(parentNetwork);
		em.flush();
	}

	/**
	 * Die Methode <code>removeNetwork</code> dient zum Löschen eines Netzwerkes
	 * aus der Datenbank. Dazu sucht man anhand der Netzwerk-Id das Netzwerk.
	 * Wurde das Netzwerk gefunden wird überprüft, ob dieses Child-Networks
	 * besitzt. Wenn nicht wird das Netzwerk gelöscht. Andernfalls wird die
	 * selbst geschriebene Exception <code>ChildNotEmptyException</code>
	 * geworfen.
	 * 
	 * @param parameterObject
	 * @throws ChildNotEmptyException
	 */
	public void removeNetwork(NetworkDetail command)
			throws ChildNotEmptyException {
		Network nw = em.find(Network.class, command.getNetworkID());

		if (nw != null) {
			if (nw.getChildren().isEmpty()) {
				em.remove(nw);
			} else {
				throw new ChildNotEmptyException(
						"Dieses Netzwerk beinhaltet Childs!");
			}
		} else {
			throw new IllegalArgumentException("Netzwerk ist null");
		}
	}

	/**
	 * Diese Methode gibt ein <code>Network-Detail</code> zurück. Wird die
	 * Methode aufgerufen, muss ein NetworkDetail-Objekt mitübergeben werden.
	 * Dieses Objekt besitzt die Id eines Netzwerkes, zu welchem die
	 * Informationen aus der Datenbank geladen werden sollen.
	 * 
	 * @param networkDetail
	 * @return Netzwerk vom Typ <code>NetworkDetail</code>
	 */
	public NetworkDetail getNetworkDetailForNetworkID(
			NetworkDetail networkDetail) {
		int networkID = networkDetail.getNetworkID();
		return mapNetworkToNetworkDetail(loadNetwork(networkID));
	}

	/**
	 * Diese Methode wandelt ein einzelnes Netzwerk in ein NetworkDetail-Objekt
	 * um.
	 * 
	 * @param network
	 * @return NetworkDetail-Objekt
	 */
	NetworkDetail mapNetworkToNetworkDetail(Network network) {
		NetworkDetail nwd = new NetworkDetail();
		nwd.setNetworkID(network.getNetworkID());
		nwd.setNetworkName(network.getNetworkName());

		Network networkParent = network.getParent();
		if (networkParent != null) {
			nwd.setNetworkParentID(networkParent.getNetworkID());
			nwd.setNetworkParentName(networkParent.getNetworkName());
		}
		return nwd;
	}

	/**
	 * Diese Methode ladet ein Netzwerk anhand dessen Id aus der Datenbank.
	 * 
	 * @param networkID
	 * @return ein Netzwerk
	 */
	Network loadNetwork(int networkID) {
		TypedQuery<Network> query = em.createQuery(
				"SELECT o FROM Network o WHERE o.networkID =" + networkID,
				Network.class);
		return query.getSingleResult();
	}

	/**
	 * Diese Methode dient zur Suche von Netzwerken, wenn man dessen Namen bzw.
	 * einen Teil des Namens kennt.
	 * 
	 * @param networkName
	 * @return Zurückgegeben wird eine Liste von Netzwerken
	 */
	public List<Network> findNetworkByName(String networkName) {
		TypedQuery<Network> query = em.createQuery(
				"SELECT o From Network o WHERE o.networkName LIKE :name",
				Network.class);
		query.setParameter("name", "%" + networkName + "%");

		List<Network> results = query.getResultList();
		return results;
	}

	/**
	 * Diese Methode gibt alle Informationen zu einem Netzwerk aus, wenn man
	 * nach dessen Namen sucht.
	 * 
	 * @param networkDetail
	 *            Übergeben wird ein NetworkDetail-Objekt, bei dem nur der Name
	 *            gesetzt wurde.
	 * @return Man bekommt eine Liste vom Typ NetworkDetail zurück.
	 */
	public List<NetworkDetail> getNetworkDetailForNetworkName(
			NetworkDetail networkDetail) {
		String networkName = networkDetail.getNetworkName();
		List<Network> results = findNetworkByName(networkName);
		return mapNetworksToNetworkDetail(results);

	}

}
