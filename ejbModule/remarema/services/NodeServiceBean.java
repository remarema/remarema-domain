package remarema.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.domain.Network;
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

    public Node createNode(int nodeID, String nodeName, String nodeNetwork, String nodeIP, String softwareVersion){
		Node n = new Node(nodeID);
		n.setNodeName(nodeName);
		n.setNodeNetwork(nodeNetwork);
		n.setNodeIP(nodeIP);
		n.setSoftwareVersion(softwareVersion);
		em.persist(n);
		return n;
	}

	public void removeNode(int nodeID){
		Node n = findNode(nodeID);
		if(n != null){
			em.remove(n);
		}
	}

	public Node findNode(int nodeID) {
		return em.find(Node.class, nodeID);
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
}
