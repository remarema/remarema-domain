package remarema.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import remarema.services.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NetworkTest {
	EntityManager entityManager = JPASetup.getDefault().createEntityManager();
	NetworkServiceBean serviceNetwork = new NetworkServiceBean(entityManager);
	Network nw;
	
	
	@Test
	public void a_erstelleNetwork1(){
		entityManager.getTransaction().begin();
		nw = serviceNetwork.createNetwork(0, "NetworkA", "192.168.1.100");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + nw);
	}
	@Test
	public void b_erstelleNetwork2(){
		entityManager.getTransaction().begin();
		nw = serviceNetwork.createNetwork(0, "NetworkB", "192.168.20.1");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + nw);
	}
	
	
	@Test
	public void c_test() {
		entityManager.find(Node.class, 1);
		entityManager.find(Network.class,1);
		entityManager.find(Deploy.class,1);
		entityManager.find(Softwarepackage.class,1);
		entityManager.find(Softwareversion.class,1);
		//entityManager.find(Settings.class,1);
	}

	@Test
	public void d_findNetwork(){
				nw = serviceNetwork.findNetwork(51);
				System.out.println("Found" + nw);
	}
	
	@Test
	public void e_findAllNetworks(){
		List<Network> networks = serviceNetwork.findAllNetworks();
		for (Network network : networks){
			System.out.println("Found Network: " + network);
		}
	}
	
	@Test
	public void f_Network_Array_ausgeben(){
		int AnzahlNetworks = serviceNetwork.findAnzahlNetworks();
		String[][][] array = new String[AnzahlNetworks][AnzahlNetworks][AnzahlNetworks];
		array = serviceNetwork.networkArray();
		
		for(int i = 1; i <AnzahlNetworks;i++){
			System.out.println("Ausgabe Network: " + array[i][0][0] +", " + array[i][i][0]+
					", " + array[i][i][i]);
		}
	}	
	@Test
	public void g_loesche_Network(){
		entityManager.getTransaction().begin();
		serviceNetwork.removeNetwork(1);
		entityManager.getTransaction().commit();
		System.out.println("Removed network 1");
	}
	
	@Test
	public void h_network_liste_ausgeben(){
		f_Network_Array_ausgeben();
	}
	
	
}
