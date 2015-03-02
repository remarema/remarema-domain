package remarema.domain;

import javax.persistence.EntityManager;

import org.junit.Test;

import remarema.services.NetworkServiceBean;

public class ModelTest {
	EntityManager entityManager = JPASetup.getDefault().createEntityManager();
	NetworkServiceBean service = new NetworkServiceBean(entityManager);

	@Test
	public void test() {
		entityManager.find(Node.class, 1);
		entityManager.find(Network.class,1);
		entityManager.find(Deploy.class,1);
		entityManager.find(Softwarepackage.class,1);
		entityManager.find(Softwareversion.class,1);
		//entityManager.find(Settings.class,1);
	}
	
	@Test
	public void erstelleNetwork(){
		entityManager.getTransaction().begin();
		Network nw = service.createNetwork(0, "NetworkA", "192.168.1.1");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + nw);
	}
	
	
	
}
