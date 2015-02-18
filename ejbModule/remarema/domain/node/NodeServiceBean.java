package remarema.domain.node;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class NodesPersistentBean
 */
@Stateless
@WebService(serviceName="NodeService")
public class NodeServiceBean implements NodeServiceBeanRemote {
	
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