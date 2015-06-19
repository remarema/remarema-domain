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
	
	private Network findNodeNetwork(String nodeNetworkName){
		if (nodeNetworkName == null) {
			return null;
		}
		TypedQuery<Network> query = em.createQuery(
				"SELECT o from Network o WHERE o.networkName = :name",
				Network.class);
		
		query.setParameter("name", nodeNetworkName);
		return query.getSingleResult();
	}

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

	public void removeNode(NodeDetail parameterObject) {
		Node n = em.find(Node.class, parameterObject.getNodeID());
		if(n != null){
			em.remove(n);
		}
	}

	public NodeDetail getNodeDetailForNodeID(NodeDetail parameterObject) {
		int nodeID = parameterObject.nodeID;
		return mapNodeToNodeDetail(loadNode(nodeID));
	}

	NodeDetail mapNodeToNodeDetail(Node node) {
		NodeDetail nd = new NodeDetail();
		nd.setNodeID(node.getID());
		nd.setNodeName(node.getNodeName());
		nd.setNodeIP(new IPAddress(node.getNodeIP()));
		nd.setSoftwareversion(node.getSoftwareVersion());
		
		Network nodeNetwork = node.getNodeNetwork();
		if(nodeNetwork != null ){
			nd.setNodeNetworkID(nodeNetwork.getNetworkID());
			nd.setNodeNetworkName(nodeNetwork.getNetworkName());
		}
		return nd;
	}

	Node loadNode(int nodeID) {
		TypedQuery<Node> query = em.createQuery(
				"SELECT n FROM Node n WHERE n.nodeID =" + nodeID, Node.class);

		return query.getSingleResult();
	}
	
	public List<NodeDetail> getNodeDetailForAllNodes(){
		
		List<Node> results = loadAllNodes();
		return mapNodesToNodeDetail(results);
	}

	private List<Node> loadAllNodes() {
		TypedQuery<Node> query = em.createQuery(
				"SELECT o FROM Node o ORDER BY o.nodeName", Node.class);
		
		List<Node> results = query.getResultList();
		return results;
	}
	
	private List<NodeDetail> mapNodesToNodeDetail(List<Node> results){
		List<NodeDetail> nodeDetail = new ArrayList<NodeDetail>();
		
		for(Node result : results){
			NodeDetail detail = new NodeDetail();
			
			detail.setNodeID(result.getID());
			detail.setNodeName(result.getNodeName());
			detail.setNodeIP(new IPAddress(result.getNodeIP()));
			detail.setSoftwareversion(result.getSoftwareVersion());
			
			if(result.hasNetwork()){
				Network nodeNetwork = result.getNodeNetwork();
				detail.setNodeNetworkID(nodeNetwork.getNetworkID());
				detail.setNodeNetworkName(nodeNetwork.getNetworkName());
			}
		
			nodeDetail.add(detail);
		}
		return nodeDetail;
	}
	
	
	public List<Node> findNodeByName(String nodeName){
		TypedQuery<Node> query = em.createQuery(
				"SELECT o From Node o WHERE o.nodeName LIKE :name", Node.class);
		query.setParameter("name", "%"+ nodeName + "%");
		
		List<Node> results = query.getResultList();
		return results;
	}
	
	public List<NodeDetail> getNodeDetailForNodeName(NodeDetail nodeDetail){
		String nodeName = nodeDetail.getNodeName();
		List<Node> results = findNodeByName(nodeName);
		return mapNodesToNodeDetail(results);
		
	}
	
	public List<NodeDetail> getNodeDetailForNetworkID(NetworkDetail networkDetail){
		int networkID = networkDetail.getNetworkID();
		List<Node> results = findNodesByNetworkId(networkID);
		return mapNodesToNodeDetail(results);
	}

	List<Node> findNodesByNetworkId(int networkID) {
		TypedQuery<Node> query = em.createQuery(
				"SELECT o From Node o WHERE o.network.networkID = "+ networkID +" ORDER BY o.nodeName ",
				Node.class);
		List<Node> nodes = query.getResultList();
		return nodes;
	}
}
