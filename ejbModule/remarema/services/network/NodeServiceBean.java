package remarema.services.network;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.network.CreateNode;
import remarema.api.network.NetworkDetail;
import remarema.api.network.NodeDetail;
import remarema.api.network.UpdateNode;
import remarema.domain.Network;
import remarema.domain.Node;

/**
 * Die Klasse <code>NodeServiceBean</code> stellt Methoden für die Verwaltung
 * von Clients bereit. Die Methoden umfassen das Anlegen, Updaten, Löschen und
 * Auslesen von Clients.
 * 
 * @author Rebecca van Langelaan
 * 
 */
@Stateless
@LocalBean
public class NodeServiceBean {

	@PersistenceContext
	protected EntityManager em;

	/**
	 * EJB default constructor.
	 */
	public NodeServiceBean() {
	}

	// Dieser Konstruktor wird nur für das Testen benötigt.
	public NodeServiceBean(EntityManager em) {
		this.em = em;
	}

	/**
	 * Die Methode <code>execute()</code> dient zur Speicherung eines neuen
	 * Clients in der Datenbank. Beim Aufruf der Methode wird als Parameter ein
	 * <code>CreateNode-Objekt</code> mitübergeben. Mit den get-Methoden kann
	 * das CreateNode-Objekt ausgelesen werden.
	 * 
	 * Anhand der {@link #findNodeNetwork(String)} - Methode wird überprüft, ob
	 * das Netzwerk, zu welchem der Client hinzugefügt werden soll, in der
	 * Datenbank existiert.
	 * 
	 * Es wird die IP-Adresse des Clients ausgelesen - anhand der
	 * IPAddress-Klasse überprüft, ob es sich dabei auch wirklich um eine
	 * IPv4-Adresse handelt. Danach wird ein neuer Client angelegt und als
	 * Parameter werden der Name und das Netzwerk mitübergeben. Die IP-Adresse
	 * wird gesetzt und der Client wird beim Netzwerk zur Clientliste
	 * hinzugefügt. Mit <code>em.persist()</code> wird der Datensatz in die
	 * Datenbank gespeichert.
	 * 
	 * @param command
	 * @throws IPNotValidException
	 * @see IPAddress
	 */
	public void execute(CreateNode command) throws IPNotValidException {
		String nodeNetworkName = command.getNodeNetworkName();
		Network nodeNetwork = findNodeNetwork(nodeNetworkName);
		String nodeIPString = command.getNodeIP();
		IPAddress ipAddress = IPAddress.parse(nodeIPString);
		int nodeIPInt = ipAddress.toInt();

		Node n = new Node(command.getNodeName(), nodeNetwork);
		n.setNodeIP(nodeIPInt);
		nodeNetwork.getNode().add(n);
		em.persist(n);
	}

	/**
	 * Diese Methode verlangt als Parameter einen Netzwerknamen. Anhand dessen
	 * wird in der Datenbank das passende Netzwerk gesucht. Falls ein Netzwerk
	 * gefunden wurde, wird dieses zurückgegeben.
	 * 
	 * @param nodeNetworkName
	 * @return Netzwerk-Objekt
	 */
	private Network findNodeNetwork(String nodeNetworkName) {
		if (nodeNetworkName == null) {
			return null;
		}
		TypedQuery<Network> query = em.createQuery(
				"SELECT o from Network o WHERE o.networkName = :name",
				Network.class);

		query.setParameter("name", nodeNetworkName);
		return query.getSingleResult();
	}

	/**
	 * Diese Methode dient zum Updaten von Clients in der Datenbank. Als
	 * Parameter wird ein <code>UpdateNode-Objekt</code> benötigt. Um das Objekt
	 * auszulesen, gibt es die passenden get-Methoden. Zuerst wird die Id und
	 * danach die IP-Adresse, die als String übergeben wurde, ausgelesen. Danach
	 * wird dieser String in eine IPAdresse umgewandelt.
	 * 
	 * Die Methode <code>em.find()</code> sucht den zur Id des Clients passenden
	 * Datensatzes in der Datenbank. Wurde ein Client gefunden, können der Name,
	 * die IP und das Netzwerk neu gesetzt werden. Mit <code>em.flush()</code>
	 * werden die Änderungen in der Datenbank übernommen.
	 * 
	 * @param command
	 * @throws IPNotValidException
	 */
	public void nodeUpdate(UpdateNode command) throws IPNotValidException {
		int nodeID = command.getNodeID();
		String nodeIPString = command.getNodeIP();
		IPAddress ipAddress = IPAddress.parse(nodeIPString);
		int nodeIPInt = ipAddress.toInt();

		Node node = em.find(Node.class, nodeID);
		node.setNodeName(command.getNodeName());
		node.setNodeIP(nodeIPInt);

		String nodeNetworkName = command.getNodeNetworkName();
		Network nodeNetwork = findNodeNetwork(nodeNetworkName);
		node.setNodeNetwork(nodeNetwork);

		em.flush();
	}

	/**
	 * Die Methode <code>removeNode()</code> benötigt als Parameter ein
	 * <code>NodeDetail-Objekt</code>. Mit dem Aufruf der Methode
	 * <code>em.find()</code> wird ein Client anhand dessen Id aus der Datenbank
	 * ausgelesen. Wenn der Client nicht null ist, wird <code>em.remove()</code>
	 * ausgeführt und wird der Client aus der Datenbank gelöscht.
	 * 
	 * @param parameterObject
	 */
	public void removeNode(NodeDetail parameterObject) {
		Node n = em.find(Node.class, parameterObject.getNodeID());
		if (n != null) {
			em.remove(n);
		}

	}

	/**
	 * Diese Methode gibt ein <code>Node-Detail</code> zurück. Wird die Methode
	 * aufgerufen, muss ein NodeDetail-Objekt mitübergeben werden. Dieses Objekt
	 * besitzt die Id eines Clients, zu welchem die Informationen aus der
	 * Datenbank geladen werden sollen.
	 * 
	 * @param nodeDetail
	 * @return Client vom Typ <code>NodeDetail</code>
	 */
	public NodeDetail getNodeDetailForNodeID(NodeDetail parameterObject) {
		int nodeID = parameterObject.nodeID;
		return mapNodeToNodeDetail(loadNode(nodeID));
	}

	/**
	 * Diese Methode wandelt einen einzelnen Client in ein NodeDetail-Objekt um.
	 * 
	 * @param node
	 * @return NodeDetail-Objekt
	 */
	NodeDetail mapNodeToNodeDetail(Node node) {
		NodeDetail nd = new NodeDetail();
		nd.setNodeID(node.getID());
		nd.setNodeName(node.getNodeName());
		nd.setNodeIP(new IPAddress(node.getNodeIP()));
		nd.setSoftwareversion(node.getSoftwareVersion());

		Network nodeNetwork = node.getNodeNetwork();
		if (nodeNetwork != null) {
			nd.setNodeNetworkID(nodeNetwork.getNetworkID());
			nd.setNodeNetworkName(nodeNetwork.getNetworkName());
		}
		return nd;
	}

	/**
	 * Die Methode benötigt als Parameter die Id eines Clients. Es wird eine
	 * TypedQuery ausgeführt. Wird ein passender Client in der Datenbank
	 * gefunden, wird der Client zurückgegeben.
	 * 
	 * @param nodeID
	 * @return Client
	 */
	Node loadNode(int nodeID) {
		TypedQuery<Node> query = em.createQuery(
				"SELECT n FROM Node n WHERE n.nodeID =" + nodeID, Node.class);

		return query.getSingleResult();
	}

	/**
	 * Wird diese Methode aufgerufen, wird eine Liste vom Typ
	 * <code>NodeDetail</code> zurückgegeben. Diese Methode wird auf der
	 * Weboberfläche im Menüpunkt "Clientverwaltung" benötigt.
	 * 
	 * @return Liste, der in der Datenbank gespeicherten Clients - aber vom Typ
	 *         <code>Node-Detail</code>
	 */
	public List<NodeDetail> getNodeDetailForAllNodes() {

		List<Node> results = loadAllNodes();
		return mapNodesToNodeDetail(results);
	}

	/**
	 * Die Methode ließt alle Clients aus der Datenbank aus und ordnet diese
	 * nach dem Clientnamen.
	 * 
	 * @return Zurückgegeben wird die Liste aller Clients
	 */
	private List<Node> loadAllNodes() {
		TypedQuery<Node> query = em.createQuery(
				"SELECT o FROM Node o ORDER BY o.nodeName", Node.class);

		List<Node> results = query.getResultList();
		return results;
	}

	/**
	 * Diese Methode "wandelt" die aus der Datenbank ausgelesenen Clients in
	 * Node-Detail-Objekte um. Diese werden dann wiederum in eine Liste
	 * gespeichert.
	 * 
	 * @param results
	 *            Als Parameter wird eine Liste vom Typ <code>Node</code>
	 *            übergeben
	 * @return Zurückgegeben wird die neue Liste vom Typ <code>NodeDetail</code>
	 */
	private List<NodeDetail> mapNodesToNodeDetail(List<Node> results) {
		List<NodeDetail> nodeDetail = new ArrayList<NodeDetail>();

		for (Node result : results) {
			NodeDetail detail = new NodeDetail();

			detail.setNodeID(result.getID());
			detail.setNodeName(result.getNodeName());
			detail.setNodeIP(new IPAddress(result.getNodeIP()));
			detail.setSoftwareversion(result.getSoftwareVersion());

			if (result.hasNetwork()) {
				Network nodeNetwork = result.getNodeNetwork();
				detail.setNodeNetworkID(nodeNetwork.getNetworkID());
				detail.setNodeNetworkName(nodeNetwork.getNetworkName());
			}

			nodeDetail.add(detail);
		}
		return nodeDetail;
	}

	/**
	 * Diese Methode dient zur Suche von Clients, wenn man dessen Namen bzw.
	 * einen Teil des Namens kennt.
	 * 
	 * @param nodeName
	 * @return Zurückgegeben wird eine Liste von Nodes
	 */
	public List<Node> findNodeByName(String nodeName) {
		TypedQuery<Node> query = em.createQuery(
				"SELECT o From Node o WHERE o.nodeName LIKE :name", Node.class);
		query.setParameter("name", "%" + nodeName + "%");

		List<Node> results = query.getResultList();
		return results;
	}

	/**
	 * Diese Methode gibt alle Informationen zu einem Client aus, wenn man nach
	 * dessen Namen sucht.
	 * 
	 * @param nodeDetail
	 *            Übergeben wird ein NodeDetail-Objekt, bei dem nur der Name
	 *            gesetzt wurde.
	 * @return Man bekommt eine Liste vom Typ NodeDetail zurück.
	 */
	public List<NodeDetail> getNodeDetailForNodeName(NodeDetail nodeDetail) {
		String nodeName = nodeDetail.getNodeName();
		List<Node> results = findNodeByName(nodeName);
		return mapNodesToNodeDetail(results);

	}

	/**
	 * Diese Methode gibt alle, in einem Netzwerk vorhandenen Clients, als Liste
	 * zurück.
	 * 
	 * @param networkDetail
	 * @return
	 */
	public List<NodeDetail> getNodeDetailForNetworkID(
			NetworkDetail networkDetail) {
		int networkID = networkDetail.getNetworkID();
		List<Node> results = findNodesByNetworkId(networkID);
		return mapNodesToNodeDetail(results);
	}

	/**
	 * Diese Methode sucht in der Datenbank nach Clients, die zu einem Netzwerk,
	 * mit der <code>networkID</code>, gehören.
	 * 
	 * @param networkID
	 * @return Zurückgegeben wird eine Liste von Nodes.
	 */
	List<Node> findNodesByNetworkId(int networkID) {
		TypedQuery<Node> query = em.createQuery(
				"SELECT o From Node o WHERE o.network.networkID = " + networkID
						+ " ORDER BY o.nodeName ", Node.class);
		List<Node> nodes = query.getResultList();
		return nodes;
	}
}
