package remarema.domain;


import java.util.List;

import javax.persistence.EntityManager;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import remarema.services.network.CreateNodeParameter;
import remarema.services.network.FindNodeNameParameter;
import remarema.services.network.FindNodeParameter;
import remarema.services.network.NodeServiceBean;
import remarema.services.network.RemoveNodeParameter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NodeTest {
	EntityManager entityManager = JPASetup.getDefault().createEntityManager();
	NodeServiceBean serviceNode = new NodeServiceBean(entityManager);
	Node n;
	
	@Test
	public void a_erstelleNode(){
		entityManager.getTransaction().begin();
		n = serviceNode.createNode(new CreateNodeParameter("Client001","192.168.1.1", 1));
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + n);
	}
	@Test
	public void b_erstelleNode2(){
		entityManager.getTransaction().begin();
		n = serviceNode.createNode(new CreateNodeParameter("Client002", "192.168.1.1", 2));
		entityManager.getTransaction().commit();
		System.out.println("Persisted " + n);
	}
	
	@Test
	public void c_test() {
		entityManager.find(Node.class, 1);
	}
	
	@Test
	public void d_findNode(){
		//find a specific node
		n = serviceNode.findNode(new FindNodeParameter(1));
		System.out.println("Found" + n);
	}
	
	@Test
	public void d_findNodeName(){
		String test = serviceNode.findNodeName(new FindNodeNameParameter(1));
		System.out.println("Habe gefunden: " + test);
		
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
	public void f_Nodes_Array_ausgeben(){
		int AnzahlNodes = serviceNode.findAnzahlNodes();
		String[][][] array = new String[AnzahlNodes][AnzahlNodes][AnzahlNodes];
		array = serviceNode.nodesArray();
		
		for(int i = 1; i <AnzahlNodes;i++){
			System.out.println("Ausgabe Node: " + array[i][0][0] +", " + array[i][i][0]+
					", " + array[i][i][i]);
		}
	}	

	/*
	@Test
	public void g_loesche_Node(){
		entityManager.getTransaction().begin();
		serviceNode.removeNode(new RemoveNodeParameter(1));
		entityManager.getTransaction().commit();
		System.out.println("Removed Node 1");
	}
	*/
	
	@Test
	public void g_nodes_liste_ausgeben(){
		f_Nodes_Array_ausgeben();
	}
	
	@Test
	public void h_suche_node_per_name(){
		List x = serviceNode.searchNodeName("Client001");
		System.out.println(x);
	}
	

}
