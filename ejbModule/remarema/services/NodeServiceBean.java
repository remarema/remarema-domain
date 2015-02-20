package remarema.services;

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

    public Node createNode(int nodeID, String nodeName, String nodeCredential, String nodeNetwork, String nodeIP, String softwareVersion){
		Node n = new Node(nodeID);
		n.setNodeName(nodeName);
		n.setNodeCredential(nodeCredential);
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

}
