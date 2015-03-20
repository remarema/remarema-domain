package remarema.services.network;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.domain.Node;

/**
 * Session Bean implementation class NodesPersistentBean
 */
@Stateless
@LocalBean
public class NodeServiceBean {
	
	@PersistenceContext
	protected EntityManager em;

    /**
     * Default constructor. 
     */
    public NodeServiceBean(EntityManager em) {
    	this.em = em;
    }

    public Node createNode(CreateNodeParameter parameterObject){
		Node n = new Node(parameterObject.nodeID);
		n.setNodeName(parameterObject.nodeName);
		n.setNodeNetworkID(parameterObject.nodeNetworkID);
		n.setNodeIP(parameterObject.nodeIP);
		n.setSoftwareVersion(parameterObject.softwareVersion);
		em.persist(n);
		return n;
	}

	public void removeNode(RemoveNodeParameter parameterObject){
		Node n = findNode(new FindNodeParameter(parameterObject.nodeID));
		if(n != null){
			em.remove(n);
		}
	}

	public Node findNode(FindNodeParameter parameterObject) {
		return em.find(Node.class, parameterObject.nodeID);
	}
	
	public List<Node> findAllNodes(){
		TypedQuery<Node> query = em.createQuery(
				"SELECT n FROM Node n", Node.class);
		return query.getResultList();
	}
	
	public int findAnzahlNodes(){
		int a = findAllNodes().size()+1;    //+1, da wir unten in der Schleife mit 1 beginnen
		return a;
	}
    
    public String[][][] nodesArray(){
    	List<Node> nodeList = new ArrayList<Node>();
    	nodeList = findAllNodes();
    	int nodesAnzahl = findAnzahlNodes();

    	String[][][] nodesString = new String[nodesAnzahl][nodesAnzahl][nodesAnzahl];
    	
    	
    	for(int i = 1; i < nodesAnzahl; i++){
    		nodesString[i][0][0] = Integer.toString(nodeList.get((i-1)).getID());
        	nodesString[i][i][0] = nodeList.get((i-1)).getNodeName();
        	nodesString[i][i][i] = nodeList.get((i-1)).getNodeIP();	
    	}
    	return nodesString;
    }
    
    public void nodeUpdate(int nodeID, String nodeName, String nodeIP, int nodeNetworkID){
    	Node n = em.find(Node.class, nodeID );
    	em.getTransaction().begin();
    	n.setNodeName(nodeName);
    	n.setNodeIP(nodeIP);
    	n.setNodeNetworkID(nodeNetworkID);
    	em.getTransaction().commit();
    }
}
