package remarema.domain;

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
    
    @WebMethod
    public Node createNode(int id, String nodeName, String nodeCredential){
		Node n = new Node(id);
		n.setNodeName(nodeName);
		n.setNodeCredential(nodeCredential);
		em.persist(n);
		return n;
	}
	
    @WebMethod
	public void removeNode(int id){
		Node n = findNode(id);
		if(n != null){
			em.remove(n);
		}
	}
	
    @WebMethod
	public Node findNode(int id) {
		return em.find(Node.class, id);
	}
	
    @WebMethod
	public List<Node> findAllNodes(){
		TypedQuery<Node> query = em.createQuery(
				"SELECT n FROM Node n",Node.class);
		return query.getResultList();
	}

}
