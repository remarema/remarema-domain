package remarema.services.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import remarema.api.CreateNode;
import remarema.api.NodeDetail;
import remarema.domain.Network;
import remarema.domain.Node;

/**
 * Die Klasse <code>NodeServiceBean</code> stellt Methoden f�r die Verwaltung
 * von Clients bereit.
 * 
 * @author Rebecca vanLangelaan
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

	public NodeServiceBean(EntityManager em) {
		this.em = em;
	}

	public void execute(CreateNode command) {
		String nodeNetworkName = command.getNodeNetworkName();
		Network nodeNetwork = findNodeNetwork(nodeNetworkName);
		
		Node n = new Node(command.getNodeName(), nodeNetwork);
		n.setNodeIP(command.getNodeIP());
		em.persist(n);
	}
	
	private Network findNodeNetwork(String nodeNetworkName){
		if (nodeNetworkName == null) {
			return null;
		}
		TypedQuery<Network> query = em.createQuery(
				"select o from Network WHERE o.networkName = :name",
				Network.class);
		
		query.setParameter("name", nodeNetworkName);
		return query.getSingleResult();
	}
	

	public void nodeUpdate(NodeDetail parameterObject) {
		Node n = em.find(Node.class, parameterObject.getNodeID());
		em.getTransaction().begin();
		n.setNodeName(parameterObject.getNodeName());
		n.setNodeIP(parameterObject.getNodeIP());
		Network network = em.find(Network.class,
				parameterObject.getNodeNetworkID());
		n.setNodeNetwork(network);
		em.getTransaction().commit();
	}

	public void removeNode(NodeDetail parameterObject) {
		Node n = em.find(Node.class, parameterObject.getNodeID());
		if (n != null) {
			em.remove(n);
		}
	}

	public Node findNode(NodeDetail parameterObject) {
		return em.find(Node.class, parameterObject.nodeID);
	}

	public List<Node> findAllNodes() {
		TypedQuery<Node> query = em.createQuery("SELECT n FROM Node n",
				Node.class);
		return query.getResultList();
	}

	public int findAnzahlNodes() {
		int a = findAllNodes().size() + 1; // +1, da wir unten in der Schleife
											// mit 1 beginnen
		return a;
	}

	public String[][][] nodesArray() {
		List<Node> nodeList = new ArrayList<Node>();
		nodeList = findAllNodes();
		int nodesAnzahl = findAnzahlNodes();

		String[][][] nodesString = new String[nodesAnzahl][nodesAnzahl][nodesAnzahl];

		for (int i = 1; i < nodesAnzahl; i++) {
			nodesString[i][0][0] = Integer.toString(nodeList.get((i - 1))
					.getID());
			nodesString[i][i][0] = nodeList.get((i - 1)).getNodeName();
			nodesString[i][i][i] = nodeList.get((i - 1)).getNodeIP();
		}
		return nodesString;
	}

	public NodeDetail getNodeDetailForNodeID(NodeDetail parameterObject) {
		int nodeID = parameterObject.nodeID;
		return mapNodeToNodeDetail(loadNode(nodeID));
	}

	NodeDetail mapNodeToNodeDetail(Node node) {
		NodeDetail nd = new NodeDetail();
		nd.setNodeID(node.getID());
		nd.setNodeName(node.getNodeName());
		nd.setNodeIP(node.getNodeIP());
		
		Network nodeNetwork = node.getNodeNetwork();
		if(nodeNetwork != null ){
			nd.setNodeNetworkID(nodeNetwork.getNetworkID());
		}

		return nd;
	}

	Node loadNode(int nodeID) {
		TypedQuery<Node> query = em.createQuery(
				"SELECT n FROM Node n WHERE n.nodeID =" + nodeID, Node.class);

		return query.getSingleResult();
	}

}