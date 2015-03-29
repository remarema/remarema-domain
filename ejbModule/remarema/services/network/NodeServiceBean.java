package remarema.services.network;

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
 * Die Klasse <code>NodeServiceBean</code> stellt Methoden f�r die Verwaltung von Clients bereit.
 * 
 *  @author Rebecca vanLangelaan
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
	public NodeServiceBean(){
	}
	
    public NodeServiceBean(EntityManager em) {
    	this.em = em;
    }

    public Node createNode(CreateNodeParameter parameterObject){
    	Network network = em.find(Network.class, parameterObject.nodeNetworkID);
		Node n = new Node(parameterObject.nodeName, network);
		n.setNodeIP(parameterObject.nodeIP);
		em.persist(n);
		return n;
	}
    
    public void nodeUpdate(NodeUpdateParameter parameterObject){
    	Node n = em.find(Node.class, parameterObject.nodeID );
    	em.getTransaction().begin();
    	n.setNodeName(parameterObject.nodeName);
    	n.setNodeIP(parameterObject.nodeIP);
    	Network network = em.find(Network.class, parameterObject.nodeNetworkID);
    	n.setNetwork(network);
    	em.getTransaction().commit();
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
	
	public String findNodeName(FindNodeNameParameter parameterObject){
		Node n = em.find(Node.class, parameterObject.nodeID);
		return n.getNodeName();
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
