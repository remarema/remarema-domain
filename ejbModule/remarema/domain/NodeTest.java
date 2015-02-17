package remarema.domain;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import remarema.domain.*;


public class NodeTest {
	

	public static void main(String[] args){
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("NodeServiceBean");
		EntityManager em = emf.createEntityManager();
		NodeServiceBean service = new NodeServiceBean(em);
	
	
	//create and persist an node
	em.getTransaction().begin();
	Node n = service.createNode(0, "Client001", "password");
	em.getTransaction().commit();
	System.out.println("Persisted " + n);
	

	
	
	//find a specific node
	n = service.findNode(1);
	System.out.println("Found" + n);
	
	//find all nodes
	List<Node> nodes = service.findAllNodes();
	for (Node node : nodes){
		System.out.println("Found Node: " + node);
	}
	
	//remove a node
	em.getTransaction().begin();
	service.removeNode(1);
	em.getTransaction().commit();
	System.out.println("Removed Node 1");
	
	//close the EM and EMF when done
	em.close();
	emf.close();
	
	
	}
}
