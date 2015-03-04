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
public class ModelTest {
	EntityManager entityManager = JPASetup.getDefault().createEntityManager();
	NetworkServiceBean serviceNetwork = new NetworkServiceBean(entityManager);
	NodeServiceBean serviceNode = new NodeServiceBean(entityManager);
	Network nw;
	Node n;
	
	@Test
	public void a_erstelleNetwork1(){
		entityManager.getTransaction().begin();
		nw = serviceNetwork.createNetwork(0, "NetworkA", "192.168.1.100");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + nw);
	}
	@Test
	public void a_erstelleNetwork2(){
		entityManager.getTransaction().begin();
		nw = serviceNetwork.createNetwork(0, "NetworkB", "192.168.20.1");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + nw);
	}
	
	@Test
	public void b_erstelleNode(){
		entityManager.getTransaction().begin();
		n = serviceNode.createNode(0,"Client001","NetworkA", "192.168.1.1", "1.0");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + n);
	}
	@Test
	public void b_erstelleNode2(){
		entityManager.getTransaction().begin();
		n = serviceNode.createNode(0,"Client002","NetworkB", "192.168.1.1", "1.0");
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + n);
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
	public void d_findNode(){
		//find a specific node
		n = serviceNode.findNode(1);
		System.out.println("Found" + n);
	}
	
	
	@Test
	public void e_findNodes(){
		//find all nodes
				List<Node> nodes = serviceNode.findAllNodes();
				for (Node node : nodes){
					System.out.println("Found Node: " + node);
				}
	}
	
	@Test
	public void f_findNetwork(){
		//find a specific network
				nw = serviceNetwork.findNetwork(51);
				System.out.println("Found" + nw);
	}
	
	@Test
	public void g_findAllNetworks(){
		//find all networks
				List<Network> networks = serviceNetwork.findAllNetworks();
				for (Network network : networks){
					System.out.println("Found Network: " + network);
				}
	}
	
	@Test
	public void h_Network_Array_ausgeben(){
		int AnzahlNetworks = serviceNetwork.findAnzahlNetworks();
		String[][][] array = new String[AnzahlNetworks][AnzahlNetworks][AnzahlNetworks];
		array = serviceNetwork.networkArray();
		
		for(int i = 1; i <AnzahlNetworks;i++){
			System.out.println("Ausgabe Network: " + array[i][0][0] +", " + array[i][i][0]+
					", " + array[i][i][i]);
		}
	}	
	
	@Test
	public void i_Nodes_Array_ausgeben(){
		int AnzahlNodes = serviceNode.findAnzahlNodes();
		String[][][] array = new String[AnzahlNodes][AnzahlNodes][AnzahlNodes];
		array = serviceNode.nodesArray();
		
		for(int i = 1; i <AnzahlNodes;i++){
			System.out.println("Ausgabe Node: " + array[i][0][0] +", " + array[i][i][0]+
					", " + array[i][i][i]);
		}
	}	
}
