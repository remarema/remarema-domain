package remarema.domain.network;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class NetworkTest {

	public static void main(String[] args){
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("openjpa");
		EntityManager em = emf.createEntityManager();
		NetworkServiceBean service = new NetworkServiceBean(em);
	
	
	//create and persist an node
	em.getTransaction().begin();
	Network nw = service.createNetwork(0, "NetworkA", "192.168.1.1");
	em.getTransaction().commit();
	System.out.println("Persisted " + nw);
	

	//find a specific node
	nw = service.findNetwork(0);
	System.out.println("Found" + nw);
	
	//find all nodes
	List<Network> networks = service.findAllNetworks();
	for (Network network : networks){
		System.out.println("Found Network: " + network);
	}
	

	//remove a node
	em.getTransaction().begin();
	service.removeNetwork(1);
	em.getTransaction().commit();
	System.out.println("Removed network 1");
	
	//close the EM and EMF when done
	em.close();
	emf.close();
	
	}
}
